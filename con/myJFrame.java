package con;

import javax.swing.*;
import java.awt.*;
public class myJFrame extends JFrame{
    private float alpha;
    public myJFrame(String bgPath,float alpha){
        super();
        myContentPane rp = new myContentPane(bgPath);
        rp.setOpaque(false);//设置内容面板为透明
        this.setContentPane(rp);
        this.setUndecorated(true);
        this.setSize(rp.img.getIconWidth(),rp.img.getIconHeight());
        this.alpha = alpha;
    }
    private class myContentPane extends JPanel{
        public ImageIcon img;
        public myContentPane(String bgPath) {
            super();
            img = new ImageIcon(login.class.getResource(bgPath));
        }
        @Override
        protected void paintComponent(Graphics g) {
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            Composite old = ((Graphics2D) g).getComposite();
            ((Graphics2D) g).setComposite(ac);
            if(img!=null){
                g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
            ((Graphics2D) g).setComposite(old);
            super.paintComponent(g);
        }
    }
}