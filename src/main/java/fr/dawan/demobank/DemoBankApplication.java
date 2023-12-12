package fr.dawan.demobank;

import fr.dawan.demobank.discord.GameMasterListener;
import fr.dawan.demobank.discord.SpeakListener;
import fr.dawan.demobank.models.Account;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoBankApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoBankApplication.class, args);
		activateDiscordBot(args[0]);
	}

	private static void activateDiscordBot(String key) {
		JDABuilder.createDefault(key)
				.setMemberCachePolicy(MemberCachePolicy.ALL)
				.enableIntents(GatewayIntent.GUILD_MEMBERS,
						GatewayIntent.GUILD_MESSAGE_REACTIONS,
						GatewayIntent.GUILD_MESSAGES,
						GatewayIntent.MESSAGE_CONTENT
				)
				.setChunkingFilter(ChunkingFilter.ALL)
				.setBulkDeleteSplittingEnabled(false)
				.setActivity(Activity.watching("le coffre de la banque"))
				.addEventListeners(new GameMasterListener()).build();
	}
}
