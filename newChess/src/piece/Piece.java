package piece;

import Model.Block;
import Model.Color;
import javafx.scene.image.ImageView;

public abstract class Piece {

    public Block currentBlock;
    protected Color color;
    public ImageView imageView;

    public Piece(Block currentBlock, ImageView imageView) {
        this.currentBlock = currentBlock;
        this.imageView = imageView;
    }

    public void move(Block UpdatedBlock) {
        this.currentBlock.setColumnIndex(UpdatedBlock.getColumnIndex());
        this.currentBlock.setRowIndex(UpdatedBlock.getRowIndex());
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
