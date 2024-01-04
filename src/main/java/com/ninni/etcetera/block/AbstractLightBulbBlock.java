package com.ninni.etcetera.block;

import com.ninni.etcetera.block.enums.LightBulbBrightness;
import com.ninni.etcetera.registry.EtceteraProperties;
import com.ninni.etcetera.registry.EtceteraSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class AbstractLightBulbBlock extends Block implements Waterloggable {
    public static final BooleanProperty HANGING = Properties.HANGING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final EnumProperty<LightBulbBrightness> BRIGHTNESS = EtceteraProperties.BRIGHTNESS;
    protected static final VoxelShape STANDING_SHAPE = Block.createCuboidShape(3, 0, 3, 13, 13, 13);
    protected static final VoxelShape HANGING_SHAPE = Block.createCuboidShape(3, 3, 3, 13, 16, 13);

    public AbstractLightBulbBlock(Settings settings) {
        super(settings);
        this.setDefaultState(((this.stateManager.getDefaultState()).with(HANGING, false)).with(WATERLOGGED, false).with(BRIGHTNESS, LightBulbBrightness.OFF));
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        for (Direction direction : ctx.getPlacementDirections()) {
            BlockState blockState;
            if (direction.getAxis() != Direction.Axis.Y || !(blockState = this.getDefaultState().with(HANGING, direction == Direction.UP)).canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) continue;
            return blockState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        }
        return null;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        this.turnBrightness(state, world, pos, state.get(BRIGHTNESS));
        return ActionResult.SUCCESS;
    }

    public void turnBrightness(BlockState state, World world, BlockPos pos, LightBulbBrightness brightness) {
        SoundEvent soundEvent = brightness == LightBulbBrightness.BRIGHT ? EtceteraSoundEvents.BLOCK_LIGHT_BULB_OFF : EtceteraSoundEvents.BLOCK_LIGHT_BULB_ON;
        world.setBlockState(pos, state.cycle(BRIGHTNESS));
        world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, 1, 1);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(HANGING) ? HANGING_SHAPE : STANDING_SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HANGING, WATERLOGGED, BRIGHTNESS);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = attachedDirection(state).getOpposite();
        return Block.sideCoversSmallSquare(world, pos.offset(direction), direction.getOpposite());
    }

    protected static Direction attachedDirection(BlockState state) {
        return state.get(HANGING) ? Direction.DOWN : Direction.UP;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (attachedDirection(state).getOpposite() == direction && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED)) return Fluids.WATER.getStill(false);
        return super.getFluidState(state);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }
}