package engine.graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import towerDefense.TowerDefense;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 * @author Pavel A Rectangle based on my other Project JBreakout. This Rectangle is based on LWJGL. I tworked once, but now it doesn't
 *         seem to work anymore
 */
public class LibGDXUnfilledRectangle extends LibGDXRectangle {

	public LibGDXUnfilledRectangle(Graphics graphics, float width, float height, Color color) {
		super(graphics, width, height, color);
	}

	public LibGDXUnfilledRectangle(Graphics graphics, float width, float height) {
		super(graphics, width, height);
	}

	public LibGDXUnfilledRectangle(Graphics graphics, float width, float height, float r, float g, float b) {
		super(graphics, width, height, r, g, b);
	}

	@Override
	public void draw(float x, float y, float globalScale) {
		// this.graphics.draw(new Rectangle(x, y, this.width * globalScale, this.height * globalScale), new GradientFill(0, 0,
		// this.color, this.width * globalScale, this.height * globalScale, this.color));
		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false, TowerDefense.getWidth(), TowerDefense.getHeight());
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 1); // r g b a
		// shapeRenderer.line(x, y, x2, y2);
		shapeRenderer.rect(x, TowerDefense.getHeight() - y, this.width * globalScale, this.height * globalScale);
		// shapeRenderer.circle(x, y, radius);
		shapeRenderer.end();
	}

}
