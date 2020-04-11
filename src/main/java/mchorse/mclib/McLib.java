package mchorse.mclib;

import java.util.Map;

import mchorse.mclib.utils.files.GlobalTree;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkCheckHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * DominionLib mod
 * 
 * Provides common code for Changeling, based on McHorse's McLib.
 */
@Mod.EventBusSubscriber
@Mod(modid = McLib.MOD_ID, name = "DominionLib", version = McLib.VERSION)
public class McLib
{
    public static final String MOD_ID = "mclib";
    public static final String VERSION = "%VERSION%";

    @NetworkCheckHandler
    public boolean checkModDependencies(Map<String, String> map, Side side)
    {
        return true;
    }
}