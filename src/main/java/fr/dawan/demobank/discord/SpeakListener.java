package fr.dawan.demobank.discord;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class SpeakListener extends ListenerAdapter {
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        // Se déclenche quand le bot et actif dans un serveur
        Guild serveur = event.getGuild(); // Référence au serveur sur lequel le bot vient de s'activer
        serveur.updateCommands().addCommands(
                Commands.slash("speak", "Faire parler son bot")
                        // Pour ajouter des paramètres à une commande
                        .addOption(OptionType.STRING, "text", "Le Texte à afficher", true)
        ).queue(); // .queue() pour ajouter l'action demandée aux tâches à réaliser du bot (asynchrone)
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        // Se déclenche lorsqu'un utilisateur utilise une commande du bot
        String name = event.getName(); // Nom de commande reçue
        if(name.equals("speak")) {
            String text = event.getOption("text").getAsString();
            event.reply(text).queue(); // reply pour indiquer le retour à effectuer
        }
    }
}
