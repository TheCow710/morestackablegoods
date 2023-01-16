package net.fabricmc.thecow.morestackable.entity;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.thecow.morestackable.ImplementedInventory;
import net.fabricmc.thecow.morestackable.MoreStackableGoods;
import net.fabricmc.thecow.morestackable.handler.ToolBeltHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ToolBeltEntity extends LootableContainerBlockEntity implements NamedScreenHandlerFactory, ImplementedInventory{

    public static final String ITEMS_KEY = "Items";

    private DefaultedList<ItemStack> items = DefaultedList.ofSize(9, ItemStack.EMPTY);

    public ToolBeltEntity(BlockPos pos, BlockState state) {
        super(MoreStackableGoods.TOOL_BELT_ENTITY ,pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, ToolBeltEntity be) {
       world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
    }

    @Override
    public void writeNbt(NbtCompound nbt){
    	super.writeNbt(nbt);
        if (!this.serializeLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.items, false);
        }
    }

    @Override
    public void readNbt(NbtCompound nbt){
        super.readNbt(nbt);
        this.readInventoryNbt(nbt);
    }
    
    public void readInventoryNbt(NbtCompound nbt) {
        this.items = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if (!this.deserializeLootTable(nbt) && nbt.contains(ITEMS_KEY, NbtElement.LIST_TYPE)) {
            Inventories.readNbt(nbt, this.items);
        }
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket(){
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(){
        return createNbt();
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }
    
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ToolBeltHandler(syncId, playerInventory, this);
        
    }
    
    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

	@Override
	protected DefaultedList<ItemStack> getInvStackList() {
		// TODO Auto-generated method stub
		return items;
	}

	@Override
	protected void setInvStackList(DefaultedList<ItemStack> list) {
		this.items = list;
		
	}

	@Override
	protected Text getContainerName() {
		// TODO Auto-generated method stub
		return Text.translatable("container.tool_belt");
	}

	@Override
	protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
		// TODO Auto-generated method stub
		return new ToolBeltHandler(syncId, playerInventory, this);
	}

    
    
}
