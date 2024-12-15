package io.github.miaow233.noperspectivechange;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NoPerspectiveChange.MODID)
public class NoPerspectiveChange {

    public static final String MODID = "noperspectivechange";
    private static final Logger LOGGER = LogUtils.getLogger();

    public NoPerspectiveChange() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
    }

    private void setup(final FMLClientSetupEvent event) {
        // 注册客户端按键监听事件
        IEventBus forgeBus = net.minecraftforge.common.MinecraftForge.EVENT_BUS;
        forgeBus.register(this);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null && minecraft.options.keyTogglePerspective.isDown()) {
            minecraft.options.keyTogglePerspective.setKey(InputConstants.UNKNOWN);
            minecraft.options.setCameraType(CameraType.FIRST_PERSON);
            // 禁止视角切换
            minecraft.player.displayClientMessage(Component.literal("Switching perspectives is not allowed!"), true);
        }
    }
}
