package com.ninni.etcetera.mixin;

import com.ninni.etcetera.registry.EtceteraItems;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {
    @Shadow @Final private Property levelCost;
    @Shadow private int repairItemUsage;

    public AnvilScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    private void hammeringAnvil(CallbackInfo ci) {
        ItemStack input = this.input.getStack(0);
        ItemStack secondStack = this.input.getStack(1);

        if (secondStack.isOf(EtceteraItems.ITEM_LABEL) && secondStack.hasCustomName() && !input.isEmpty()) {
            ci.cancel();
            ItemStack output = input.copy();
            NbtCompound nbt = output.getOrCreateNbt();
            String labelText = secondStack.getName().getString();

            if (!output.getNbt().contains("Label1")) {
                nbt.putString("Label1", labelText);
                this.levelCost.set(1);
            } else if (output.getNbt().contains("Label1") && !output.getNbt().contains("Label2")) {
                nbt.putString("Label2", labelText);
                this.levelCost.set(2);
            } else if (output.getNbt().contains("Label2") && !output.getNbt().contains("Label3")) {
                nbt.putString("Label3", labelText);
                this.levelCost.set(3);
            } else if (output.getNbt().contains("Label2") && output.getNbt().contains("Label3") && !output.getNbt().contains("Label4")) {
                nbt.putString("Label4", labelText);
                this.levelCost.set(4);
            } else {
                this.output.setStack(0, ItemStack.EMPTY);
                this.levelCost.set(0);
            }

            this.repairItemUsage = 1;
            this.output.setStack(0, output);
            this.sendContentUpdates();
        }
        if (input.isOf(EtceteraItems.GOLDEN_GOLEM) && secondStack.isOf(Items.GOLD_INGOT) && input.hasNbt() && input.getNbt().contains("HealingAmount") && input.getNbt().getInt("HealingAmount") < 10) {
            ci.cancel();
            ItemStack output = input.copy();
            NbtCompound nbt = output.getNbt();

            nbt.putInt("HealingAmount", Math.min(nbt.getInt("HealingAmount") + 2, 10));
            if (nbt.contains("Broken") && nbt.getBoolean("Broken")) nbt.putBoolean("Broken", false);
            this.levelCost.set(2);

            this.repairItemUsage = 1;
            this.output.setStack(0, output);
            this.sendContentUpdates();
        }
    }
}
