//======================================================
// Source code generated by jvider v1.8.1 UNREGISTERED version.
// http://www.jvider.com/
//======================================================
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
public class PauseFrame extends JFrame 
{
static PauseFrame thePauseFrame;

JPanel panel;
ButtonGroup rbgpanel;
JRadioButton animationButton;
JRadioButton soundButton;
JTextField name;
JLabel playerLabel;
JTable localRecordsasa;
JTable table2;
public static void main(String args[]) 
{
try 
{
UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
}
catch (ClassNotFoundException e) 
{
}
catch (InstantiationException e) 
{
}
catch (IllegalAccessException e) 
{
}
catch (UnsupportedLookAndFeelException e) 
{
}
thePauseFrame = new PauseFrame();
} 

public PauseFrame() 
{
super("TITLE");

panel = new JPanel();
panel.setBorder(BorderFactory.createTitledBorder("Options"));
panel.setName("panel");
rbgpanel = new ButtonGroup();
GridBagLayout gbpanel = new GridBagLayout();
GridBagConstraints gbcpanel = new GridBagConstraints();
panel.setLayout(gbpanel);

animationButton = new JRadioButton("animation");
rbgpanel.add(animationButton);
gbcpanel.gridx = 1;
gbcpanel.gridy = 0;
gbcpanel.gridwidth = 2;
gbcpanel.gridheight = 1;
gbcpanel.fill = GridBagConstraints.BOTH;
gbcpanel.weightx = 1;
gbcpanel.weighty = 0;
gbcpanel.anchor = GridBagConstraints.NORTHEAST;
gbpanel.setConstraints(animationButton, gbcpanel);
panel.add(animationButton);

soundButton = new JRadioButton("sound");
rbgpanel.add(soundButton);
gbcpanel.gridx = 1;
gbcpanel.gridy = 1;
gbcpanel.gridwidth = 2;
gbcpanel.gridheight = 1;
gbcpanel.fill = GridBagConstraints.BOTH;
gbcpanel.weightx = 1;
gbcpanel.weighty = 0;
gbcpanel.anchor = GridBagConstraints.NORTHEAST;
gbpanel.setConstraints(soundButton, gbcpanel);
panel.add(soundButton);

name = new JTextField();
gbcpanel.gridx = 0;
gbcpanel.gridy = 1;
gbcpanel.gridwidth = 1;
gbcpanel.gridheight = 1;
gbcpanel.fill = GridBagConstraints.BOTH;
gbcpanel.weightx = 1;
gbcpanel.weighty = 0;
gbcpanel.anchor = GridBagConstraints.NORTHWEST;
gbpanel.setConstraints(name, gbcpanel);
panel.add(name);

playerLabel = new JLabel("Player Name");
gbcpanel.gridx = 0;
gbcpanel.gridy = 0;
gbcpanel.gridwidth = 1;
gbcpanel.gridheight = 1;
gbcpanel.fill = GridBagConstraints.BOTH;
gbcpanel.weightx = 1;
gbcpanel.weighty = 1;
gbcpanel.anchor = GridBagConstraints.NORTHWEST;
gbpanel.setConstraints(playerLabel, gbcpanel);
panel.add(playerLabel);

String [][]datalocalRecordsasa = new String[][] {new String[] {"11","21"},
                                             new String[] {"12","22"},
                                             new String[] {"13","23"}};
String []colslocalRecordsasa = new String[] {"",""};
localRecordsasa = new JTable(datalocalRecordsasa,colslocalRecordsasa);
localRecordsasa.setRowHeight(18);
localRecordsasa.setToolTipText("Local records");
JScrollPane scplocalRecordsasa = new JScrollPane(localRecordsasa);
gbcpanel.gridx = 0;
gbcpanel.gridy = 2;
gbcpanel.gridwidth = 1;
gbcpanel.gridheight = 1;
gbcpanel.fill = GridBagConstraints.BOTH;
gbcpanel.weightx = 1;
gbcpanel.weighty = 1;
gbcpanel.anchor = GridBagConstraints.WEST;
gbpanel.setConstraints(scplocalRecordsasa, gbcpanel);
panel.add(scplocalRecordsasa);

String [][]datatable2 = new String[][] {new String[] {"11","21"},
                                        new String[] {"12","22"},
                                        new String[] {"13","23"}};
String []colstable2 = new String[] {"",""};
table2 = new JTable(datatable2,colstable2);
table2.setRowHeight(18);
gbcpanel.gridx = 2;
gbcpanel.gridy = 2;
gbcpanel.gridwidth = 1;
gbcpanel.gridheight = 1;
gbcpanel.fill = GridBagConstraints.BOTH;
gbcpanel.weightx = 1;
gbcpanel.weighty = 1;
gbcpanel.anchor = GridBagConstraints.EAST;
gbpanel.setConstraints(table2, gbcpanel);
panel.add(table2);

setDefaultCloseOperation(EXIT_ON_CLOSE);

setContentPane(panel);
pack();
setVisible(true);
} 
} 
