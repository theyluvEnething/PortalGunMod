package com.enething.portalgun.entity;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;

public class EntityPortalProjectile extends Entity {


    public boolean ORANGE = true;
    public int age = 0;
    private LivingEntity shooter;
    private double x, y, z;
    private double motionX, motionY, motionZ, rotationYaw, prevRotationYaw, prevRotationPitch, rotationPitch;

    public EntityPortalProjectile(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public EntityPortalProjectile(EntityType<?> pEntityType, Level pLevel, double x, double y, double z, LivingEntity shooterPlayer
    ) {
        super(pEntityType, pLevel);
        this.shooter = shooterPlayer;
        this.x = x;
        this.y = y;
        this.z = z;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
    }

    protected void onImpact(Level pLevel, HitResult impact) {
        if(!pLevel.isClientSide()) {
            BlockPos blockImpact = new BlockPos(impact.getLocation());
            pLevel.setBlock(blockImpact, Blocks.IRON_BLOCK.defaultBlockState() ,16);
        }
    }
    public void setPostion() {
        this.x = motionX;
        this.y = motionY;
        this.z = motionZ;
    }

    public void shoot(double x, double y, double z, float velocity)
    {
        float f = Mth.sqrt((float) (x * x + y * y + z * z));
        x = x / (double)f;
        y = y / (double)f;
        z = z / (double)f;
        x = x * (double)velocity;
        y = y * (double)velocity;
        z = z * (double)velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f1 = Mth.sqrt((float) (x * x + z * z));
        this.rotationYaw = (float)(Mth.atan2(x, z) * (180D / Math.PI));
        this.rotationPitch = (float)(Mth.atan2(y, (double)f1) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return null;
    }
}
