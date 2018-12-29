package ejektaflex.bountiful.api.logic.pickable

import net.minecraft.nbt.NBTTagCompound

open class PickedEntry(override var contentID: String = "UNDEFINED", override var amount: Int = Integer.MIN_VALUE) : IPickedEntry {
    override fun serializeNBT(): NBTTagCompound {
        return NBTTagCompound().apply {
            setString("contentID", contentID)
            setInteger("amount", amount)
        }
    }

    override fun deserializeNBT(tag: NBTTagCompound) {
        contentID = tag.getString("contentID")
        amount = tag.getInteger("amount")
    }

    override val pretty: String
        get() = "§f${amount}x §6$contentID"

    override fun typed(): IPickedEntry {
        if (":" in contentID) {
            return when (val type = contentID.substringBefore(':')) {
                "doot" -> PickedEntryStack(this)
                else -> PickedEntryStack(this)
            }
        } else {
            throw Exception("Entry: '$contentID' is has no comma prefix!")
        }
    }

    override val content: Any? = null

}