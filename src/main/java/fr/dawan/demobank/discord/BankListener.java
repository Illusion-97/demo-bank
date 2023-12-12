package fr.dawan.demobank.discord;

import fr.dawan.demobank.serveur.BankService;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.List;
import java.util.Objects;

public class BankListener extends ListenerAdapter {
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        Guild serveur = event.getGuild();
        List<Member> members = serveur.getMembers(); // membres présents sur un serveur
        members.stream()
                .filter(member -> !member.getUser().isBot()) // récupère uniquement les membres qui ne sont pas des bots
                //.map(member -> member.getEffectiveName())
                .map(Member::getEffectiveName)
                //.forEach(nom -> BankService.createAccount(nom));
                .forEach(BankService::createAccount);
        serveur.updateCommands().addCommands(
                Commands.slash("check", "Consultation du compte"),
                Commands.slash("generate", "Génération du code du compte")

        ).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String name = event.getName();
        try {
            String memberName = Objects.requireNonNull(event.getMember()).getEffectiveName();
            switch (name) {
                case "check" -> event.reply(BankService.getAccountData(memberName)).queue();
                case "generate" -> event.reply(BankService.generateCode(memberName)).setEphemeral(true).queue();
            }
        } catch (Exception e) {
            event.reply(e.getMessage()).setEphemeral(true).queue();
        }
    }
}
