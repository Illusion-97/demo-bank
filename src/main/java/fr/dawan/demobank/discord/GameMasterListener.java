package fr.dawan.demobank.discord;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Objects;

public class GameMasterListener extends ListenerAdapter {
    /*
    * Créer une commande de jet de dés
    * à saisir : le nombre de dés à lancer
    * le nombre de faces du dé
    *
    * en réponse : la somme des jets de dés aléatoire
    * */
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        // Se déclenche quand le bot et actif dans un serveur
        Guild serveur = event.getGuild(); // Référence au serveur sur lequel le bot vient de s'activer
        serveur.updateCommands().addCommands(
                Commands.slash("jet", "Faire un jet de dés")
                        // Pour ajouter des paramètres à une commande
                        .addOption(OptionType.INTEGER, "faces", "Nombre de faces du dé", true)
                        .addOption(OptionType.INTEGER, "qte", "Nombre de dés à lancer", true)
        ).queue(); // .queue() pour ajouter l'action demandée aux tâches à réaliser du bot (asynchrone)
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        // Se déclenche lorsqu'un utilisateur utilise une commande du bot
        String name = event.getName(); // Nom de commande reçue
        if(name.equals("jet")) try {
            int[] numbers = getNumbers(event);
            int[] ints = SECURE_RANDOM.ints(numbers[0], 1, numbers[1] + 1).toArray();
            event.reply("Résultat des jets : %s. Total : %d".formatted(Arrays.toString(ints), Arrays.stream(ints).sum())).queue(); // reply pour indiquer le retour à effectuer
        } catch (Exception e) {
            event.reply(e.getMessage()).setEphemeral(true).queue();
        }
    }

    private int[] getNumbers(SlashCommandInteractionEvent event) {
        int number = Objects.requireNonNull(event.getOption("qte")).getAsInt();
        if(number < 1 || number > 3) {
            throw new RuntimeException("Nombre de dé incorrect : (1-3)");
        }
        int faces = Objects.requireNonNull(event.getOption("faces")).getAsInt();
        if(faces < 1 || faces > 100) {
            throw new RuntimeException("Le nombre de faces incorrect : (1-100)");
        }
        return new int[]{number, faces};
    }
}
