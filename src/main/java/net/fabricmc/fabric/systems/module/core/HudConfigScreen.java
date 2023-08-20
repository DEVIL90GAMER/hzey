package net.fabricmc.fabric.systems.module.core;

import net.fabricmc.fabric.systems.module.impl.KillerKlient.HudModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class HudConfigScreen extends Screen {

    // For every Hud mod there is a Draggable component, so you can drag it around
    public static final List<DraggableComponent> components = new ArrayList<>();

    public HudConfigScreen() {
        super(Text.of("Hud Config Screen"));
    }

    @Override
    protected void init() {
        // Add a new component for all enabled Hud Modules
        components.clear();
        // Goes through all Modules that are Hud Modules and adds a new component
        for(Module module : ModuleManager.INSTANCE.getModules()) {
            if(module.isEnabled() && module instanceof HudModule hm) components.add(new DraggableComponent(hm));
        }
        super.init();
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta) {
        // Draw the Components if the corresponding Hud Module is enabled
        components.forEach(drag -> {
            if(drag.getModule().isEnabled())
                drag.render(drawContext, mouseX, mouseY);
        });

        super.render(drawContext, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        components.forEach(drag -> drag.mouseClicked(mouseX, mouseY, button));
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        components.forEach(drag -> drag.mouseReleased(mouseX, mouseY, button));
        return super.mouseReleased(mouseX, mouseY, button);
    }
}