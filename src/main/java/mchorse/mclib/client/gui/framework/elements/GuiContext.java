package mchorse.mclib.client.gui.framework.elements;

import mchorse.mclib.client.gui.framework.GuiBase;
import mchorse.mclib.client.gui.framework.GuiTooltip;
import mchorse.mclib.client.gui.framework.elements.context.GuiContextMenu;

import java.util.List;

public class GuiContext
{
	public final GuiBase screen;
	public final GuiTooltip tooltip;

	public int mouseX;
	public int mouseY;
	public int mouseButton;
	public int mouseWheel;

	public char typedChar;
	public int keyCode;

	public float partialTicks;

	public IFocusedGuiElement activeElement;
	public GuiContextMenu contextMenu;

	public GuiContext(GuiBase screen)
	{
		this.screen = screen;
		this.tooltip = new GuiTooltip();
	}

	public void setMouse(int mouseX, int mouseY)
	{
		this.mouseX = mouseX;
		this.mouseY = mouseY;
	}

	public void setMouse(int mouseX, int mouseY, int mouseButton)
	{
		this.setMouse(mouseX, mouseY);
		this.mouseButton = mouseButton;
	}

	public void setMouseWheel(int mouseX, int mouseY, int mouseWheel)
	{
		this.setMouse(mouseX, mouseY);
		this.mouseWheel = mouseWheel;
	}

	public void setKey(char typedChar, int keyCode)
	{
		this.typedChar = typedChar;
		this.keyCode = keyCode;
	}

	public boolean isFocused()
	{
		return this.activeElement != null;
	}

	public void focus(IFocusedGuiElement element)
	{
		if (this.activeElement == element)
		{
			return;
		}

		if (this.activeElement != null)
		{
			this.activeElement.unfocus(this);
		}

		this.activeElement = element;

		if (this.activeElement != null)
		{
			this.activeElement.focus(this);
		}
	}

	public void unfocus()
	{
		this.focus(null);
	}

	/**
	 * Focus next focusable GUI element
	 */
	public void focus(GuiElement parent, int index, int factor)
	{
		List<IGuiElement> children = parent.getChildren();
		boolean stop = factor == 0;

		factor = factor >= 0 ? 1 : -1;
		index += factor;

		for (; index >= 0 && index < children.size(); index += factor)
		{
			IGuiElement child = children.get(index);

			if (child instanceof IFocusedGuiElement && child.isEnabled())
			{
				this.focus((IFocusedGuiElement) child);

				return;
			}
			else if (child instanceof GuiElement)
			{
				this.focus((GuiElement) child, -1, 0);
			}
		}

		GuiElement grandparent = parent.getParent();

		if (grandparent != null && !stop)
		{
			this.focus(grandparent, grandparent.getChildren().indexOf(parent), factor);
		}
	}

	public boolean hasContextMenu()
	{
		if (this.contextMenu == null)
		{
			return false;
		}

		if (!this.contextMenu.hasParent())
		{
			this.contextMenu = null;
		}

		return this.contextMenu != null;
	}

	public void setContextMenu(GuiContextMenu menu)
	{
		if (this.hasContextMenu() || menu == null)
		{
			return;
		}

		menu.setMouse(this);
		menu.resize();

		this.contextMenu = menu;
		this.screen.root.add(menu);
	}
}