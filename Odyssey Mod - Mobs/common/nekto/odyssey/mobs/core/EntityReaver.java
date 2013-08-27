package nekto.odyssey.mobs.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTwardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityReaver extends EntityMob
{

    public EntityReaver(World par1World)
    {
        super(par1World);
        this.texture = "/mods/odyssey/textures/entity/Reaver.png";
        this.moveSpeed = 0.4F;
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIBreakDoor(this));
        tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, moveSpeed, false));
        tasks.addTask(4, new EntityAIMoveTwardsRestriction(this, moveSpeed));
        tasks.addTask(5, new EntityAIMoveThroughVillage(this, moveSpeed, false));
        tasks.addTask(6, new EntityAIWander(this, moveSpeed));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(7, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
    }

    @Override
    public int getMaxHealth()
    {
        return 30;
    }

    @Override
    public int getAttackStrength(Entity par1Entity)
    {
        ItemStack itemstack = this.getHeldItem();
        int i = 6;
        
        if (itemstack != null)
        {
            i += itemstack.getDamageVsEntity(this);
        }
        
        return i;
    }
    
    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }
    
    @Override
    protected void addRandomArmor()
    {
        this.setCurrentItemOrArmor(0, new ItemStack(Item.bow));
    }
}
