package space.bbkr.aquarius;

import net.minecraft.block.BlockConduit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Fluids;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReaderBase;

public class BlockChorusConduit extends BlockConduit {

    public BlockChorusConduit(Builder builder) {
        super(builder);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader reader) {
        return new TileEntityChorusConduit();
    }

    @Override
    public boolean isValidPosition(IBlockState p_isValidPosition_1_, IWorldReaderBase p_isValidPosition_2_, BlockPos p_isValidPosition_3_) {
        return true;
    }

    @Override
    public IFluidState getFluidState(IBlockState state) {
        return Fluids.EMPTY.getDefaultState();
    }

}
