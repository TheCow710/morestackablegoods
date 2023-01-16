package net.fabricmc.thecow.morestackable;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.thecow.morestackable.blocks.ToolBelt;
import net.fabricmc.thecow.morestackable.entity.ToolBeltEntity;
import net.fabricmc.thecow.morestackable.handler.ToolBeltHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class MoreStackableGoods implements ModInitializer{

    public static final String ID = "morestackable";
    
    //Items
    public static final Item DIAMOND_GEARS = new Item(new FabricItemSettings().maxCount(1));

    //Entity
    public static BlockEntityType<ToolBeltEntity> TOOL_BELT_ENTITY;

    //Handlers
    public static ScreenHandlerType<ToolBeltHandler> TOOL_BELT_HANDLER;

    //Blocks
    public static final ToolBelt TOOL_BELT = new ToolBelt(FabricBlockSettings.copy(Blocks.CRAFTING_TABLE).nonOpaque().strength(0.5F).sounds(BlockSoundGroup.WOOD));
    public static final Block POTATO_BLOCK = new Block(FabricBlockSettings.of(Material.PLANT).strength(0.5F).sounds(BlockSoundGroup.CROP));
    public static final Block CARROT_BLOCK = new Block(FabricBlockSettings.of(Material.PLANT).strength(0.5F).sounds(BlockSoundGroup.CROP));
    public static final Block SUGAR_CANE_BLOCK = new Block(FabricBlockSettings.of(Material.PLANT).strength(0.5F).sounds(BlockSoundGroup.CROP));
    public static final Block WHEAT_SEED_BLOCK = new Block(FabricBlockSettings.of(Material.PLANT).strength(0.5F).sounds(BlockSoundGroup.CROP));
    public static final Block BEET_SEED_BLOCK = new Block(FabricBlockSettings.of(Material.PLANT).strength(0.5F).sounds(BlockSoundGroup.CROP));

    //ItemGroup
    public static final ItemGroup MoreStackable = FabricItemGroup.builder(new Identifier(ID, "morestackablegoods"))
        .icon(() -> new ItemStack(TOOL_BELT))
        .build();
        
    @Override
    public void onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(MoreStackable).register(content -> {
            content.add(TOOL_BELT);
            content.add(DIAMOND_GEARS);
            content.add(POTATO_BLOCK);
            content.add(CARROT_BLOCK);
            content.add(SUGAR_CANE_BLOCK);
            content.add(WHEAT_SEED_BLOCK);
            content.add(BEET_SEED_BLOCK);
        });

        //Items
        Registry.register(Registries.ITEM, new Identifier(ID, "diamond_gears"), DIAMOND_GEARS);

        //Blocks
        Registry.register(Registries.BLOCK, new Identifier(ID, "tool_bench"), TOOL_BELT);
        Registry.register(Registries.BLOCK, new Identifier(ID, "potato_block"), POTATO_BLOCK);
        Registry.register(Registries.BLOCK, new Identifier(ID, "carrot_block"), CARROT_BLOCK);
        Registry.register(Registries.BLOCK, new Identifier(ID, "sugar_cane_block"), SUGAR_CANE_BLOCK);
        Registry.register(Registries.BLOCK, new Identifier(ID, "wheat_seed_block"), WHEAT_SEED_BLOCK);
        Registry.register(Registries.BLOCK, new Identifier(ID, "beetroot_seed_block"), BEET_SEED_BLOCK);

        //ItemBlocks
        Registry.register(Registries.ITEM, new Identifier(ID, "tool_bench"), new BlockItem(TOOL_BELT, new FabricItemSettings().maxCount(1)));
        Registry.register(Registries.ITEM, new Identifier(ID, "potato_block"), new BlockItem(POTATO_BLOCK, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(ID, "carrot_block"), new BlockItem(CARROT_BLOCK, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(ID, "sugar_cane_block"), new BlockItem(SUGAR_CANE_BLOCK, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(ID, "wheat_seed_block"), new BlockItem(WHEAT_SEED_BLOCK, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(ID, "beetroot_seed_block"), new BlockItem(BEET_SEED_BLOCK, new FabricItemSettings()));
        
        //BlockEntities
            TOOL_BELT_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(ID, "tool_belt_entity"),
            FabricBlockEntityTypeBuilder.create(ToolBeltEntity::new, TOOL_BELT).build()
        );

        //Handler
        TOOL_BELT_HANDLER = new ScreenHandlerType<>(ToolBeltHandler::new);
        
    }
    
}
