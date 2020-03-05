package ejektaflex.bountiful

import net.minecraftforge.common.ForgeConfigSpec
import org.apache.commons.lang3.tuple.Pair as ApachePair

class BountifulConfig {

    companion object {

        val specPair: ApachePair<Server, ForgeConfigSpec> = ForgeConfigSpec
                .Builder()
                .configure<Server> {
                    Server(it)
                }

        val serverSpec = specPair.right
        val SERVER: Server = specPair.left

    }

    class Server(b: ForgeConfigSpec.Builder) {
        var maxBountiesPerBoard: ForgeConfigSpec.IntValue = b.comment(
                "The maximum number of bounties present at a given board " +
                        "before it must delete some bounties to make room for more."
        ).defineInRange("maxBountiesPerBoard", 15, 1, 21)

        var boardAddFrequency: ForgeConfigSpec.IntValue = b
                .comment("How often, in seconds, we should be adding to the bounty board.")
                .defineInRange("boardCreationFrequency", 90, 1, 100000)

        var boardLifespan: ForgeConfigSpec.IntValue = b
                .comment("How long, in seconds, a bounty should be" +
                " able to stay on a board (if it ISN'T pushed off by another bounty).")
                .defineInRange("boardLifespan", 3600, 60, 600000)

        var timeMultiplier: ForgeConfigSpec.DoubleValue = b
                .comment("A global multiplier for the time needed to complete a bounty.")
                .defineInRange("timeMultiplier", 7.5, 1.0, 10000.0)

        var cashInAtBountyBoard: ForgeConfigSpec.BooleanValue = b
                .comment("If true, you can fulfill bounties by right clicking on a bounty board.")
                .define("cashInAtBoard", true)

        var rarityChance: ForgeConfigSpec.DoubleValue = b
                .comment(
                        "The odds of any given bounty going from one tier up to the next.",
                        "(Higher Rarity = Higher chance of more rare rewards to show up.)",
                        "At 0.0, all bounties will be common. At 1.0, all bounties will be epic.",
                        "At 0.5, there is a 50% chance of going from any rarity to the next.")
                .defineInRange("rarityTierUpChance", 0.4, 0.0, 1.0)

        var bountyTimeMin: ForgeConfigSpec.IntValue = b
                .comment("The minimum amount of time you should get to complete a bounty.")
                .defineInRange("minBountyTime", 300, 10, 600000)

        var shouldCountdownOnBoard: ForgeConfigSpec.BooleanValue = b
                .comment("Whether bounties should start counting down as soon as they are created")
                .define("instantCountdown", false)

        var namespaceBlacklist: ForgeConfigSpec.ConfigValue<List<String>> = b
                .comment("Namespaces (mod ids) that should get blacklisted from loading bounty data")
                .define("blacklistedDataNamespaces", listOf())

    }

    var debugMode = true


}