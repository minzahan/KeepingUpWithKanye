import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainPage extends JFrame implements ActionListener
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel centerPanel;
	public JPanel centerTopPanel;
    public JPanel centerBottomPanel;
    public JLabel lblTerm;
	public JTextField txtSearchTerm;
	private JButton btnSearch;
    public JLabel lblAlbum;
	public JLabel lblTrack;
	public JLabel lblCount;
	
    public MainPage(String title)
    {
        super(title);
        setSize(640, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLayout(new BorderLayout());
		add(new JLabel(new ImageIcon("banner2.jpg")), BorderLayout.NORTH);
        centerTopPanel = new JPanel();
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        centerTopPanel.setLayout(gridBag);
        lblTerm = new JLabel("Enter word");
		txtSearchTerm = new JTextField(30);
		btnSearch = new JButton("Search");
        Font pcf = new Font("SansSerif", Font.PLAIN, 16);
        lblTerm.setFont(pcf);
		txtSearchTerm.setFont(pcf);
		btnSearch.setFont(pcf);
		btnSearch.addActionListener(this);
        gbc.gridx = 0;
		gbc.gridy = 0;
		centerTopPanel.add(lblTerm, gbc);
        
        gbc.gridx = 1;
		gbc.gridy = 0;
		centerTopPanel.add(txtSearchTerm, gbc);
        
        gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		centerTopPanel.add(btnSearch, gbc);
    
        
        centerPanel = new JPanel(new FlowLayout());
    
        centerPanel.add(centerTopPanel);
        //centerPanel.add(centerBottomPanel, BorderLayout.CENTER);
        
        add(centerPanel, BorderLayout.CENTER);
        Helper.readAlbums();

    }
    
    public static void main(String [] args)
    {
        
        MainPage win = new MainPage("Kanye West Lyrics Search");
        win.setVisible(true);
        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		String searchTerm = txtSearchTerm.getText();
        
        //JOptionPane.showMessageDialog(this, "you entered "+ searchTerm);
        Helper.readAlbums();
        ArrayList<ArrayList<String>> found = Helper.searchLyrics(searchTerm);
        
        System.out.println("The term " + searchTerm + " has been found in the followng tracks");
        System.out.println(found);
        
        int noOfRows = found.size();
        
        
        GridLayout centerBottomLayout = new GridLayout(noOfRows + 1, 3);
		centerBottomPanel = new JPanel(centerBottomLayout);
		centerBottomLayout.setHgap(1);
		centerBottomLayout.setVgap(1);
	    Font rf = new Font("SansSerif", Font.BOLD, 14);
        lblAlbum = new JLabel("Album");
		lblAlbum.setFont(rf);
		lblTrack = new JLabel("Track");
		lblTrack.setFont(rf);
		lblCount = new JLabel("Count");
		lblCount.setFont(rf);

		centerBottomPanel.removeAll();

		int count = 0;
		for(ArrayList<String> al : found)
        {
        	centerBottomPanel.add(new JLabel(al.get(0).toString()), count, 0);
        	centerBottomPanel.add(new JLabel(al.get(1).toString() + " : " +
        					al.get(2).toString()), count, 1);
        	centerBottomPanel.add(new JLabel(al.get(3).toString()), count, 2);
        	count++;
        }
		centerBottomPanel.add(lblAlbum, noOfRows, 0);
		centerBottomPanel.add(lblTrack, noOfRows, 1);
		centerBottomPanel.add(lblCount, noOfRows, 2);
       
		centerBottomPanel.revalidate();
        centerPanel.add(centerBottomPanel);
        centerPanel.revalidate();
        this.revalidate();
	}
    
    
    
}