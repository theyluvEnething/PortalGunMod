package com.enething.portalgun.item;

import com.enething.portalgun.entity.EntityPortalProjectile;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class ItemPortalGun extends Item {
    public ItemPortalGun(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        Vec3 direction = pPlayer.getLookAngle();
        EntityPortalProjectile portalProjectile = new EntityPortalProjectile(EntityType.ENDER_PEARL, pLevel, 1.0D, 1.0D, 1.0D, pPlayer);
        portalProjectile.shoot(pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), 1.9999f);
        portalProjectile.setPostion();
        if (!pLevel.isClientSide()) {
            pLevel.addFreshEntity(portalProjectile);
        }
        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 1.0f, 1.0f);
        return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, stack);
    }

    // Test
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        BlockPos positionClicked = pContext.getClickedPos();
        Player player = pContext.getPlayer();

        Block target = pContext.getLevel().getBlockState(positionClicked).getBlock();

        if (target == Blocks.DIAMOND_BLOCK) {
            player.sendMessage(new TextComponent("BlockPos: (" + positionClicked.getX() + ", " + positionClicked.getY() + ", " + positionClicked.getZ() + ")"),
                    player.getUUID());
        }
        return super.useOn(pContext);
    }
}
