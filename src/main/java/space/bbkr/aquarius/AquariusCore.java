package space.bbkr.aquarius;

import org.dimdev.riftloader.listener.InitializationListener;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

public class AquariusCore implements InitializationListener {
    @Override
    public void onInitialization() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.aquarius.json");
    }
}
