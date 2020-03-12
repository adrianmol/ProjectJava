/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author Jhon
 */
public class NewClass extends Frame implements ActionListener{
    JButton Open,Clear,Save,Copy;
    TextField TxtFld;
    TextArea TxtArea;
    private MenuBar bar;
    private Menu FileMenu;
    private Menu EditMenu;
    private MenuItem openItem,saveItem,closeItem,clearItem,copyItem,statisticsItem;
    private Panel panel1;
    
   private GridBagConstraints grbc = new GridBagConstraints(); 
    public NewClass() {
        
        bar=new MenuBar();
        FileMenu=new Menu("file");
        openItem=new MenuItem("open");
        saveItem=new MenuItem("save");
        statisticsItem=new MenuItem("statistics");
        
        closeItem=new MenuItem("close");
        
        FileMenu.add(openItem);
        FileMenu.add(saveItem);
        FileMenu.add(statisticsItem);
        FileMenu.addSeparator();
        FileMenu.add(closeItem);
       
        
        
        EditMenu=new Menu("edit");
        clearItem=new MenuItem("clear");
        copyItem=new MenuItem("copy");
        
        EditMenu.add(clearItem);
        EditMenu.add(copyItem);
        
        statisticsItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        closeItem.addActionListener(this);
        clearItem.addActionListener(this);
        copyItem.addActionListener(this);
        
         bar.add(FileMenu);
         bar.add(EditMenu);
         this.setMenuBar(bar);
        
      
     
       GridBagLayout fl=new GridBagLayout();
        this.setLayout(fl);   
        grbc.fill = GridBagConstraints.BOTH;
        
        panel1 = new Panel(new FlowLayout(FlowLayout.LEFT));
          Open=new JButton("Open");
        
        Clear=new JButton("Clear");
        Save=new JButton("Save");
        Copy=new JButton("Copy");
        TxtFld=new TextField(80);
        TxtArea=new TextArea();
        Open.addActionListener(this);
        Clear.addActionListener(this);
        Save.addActionListener(this);
        Copy.addActionListener(this);
 
        
        this.addWindowListener(new Clistener());
      
        
        panel1.add(Open);
        panel1.add(Save);
        panel1.add(Clear);
        panel1.add(Copy);
        
    
        grbc.gridx=0;grbc.gridy=0;
        grbc.gridheight = 1; grbc.weightx = 0.5; grbc.weighty = 0;
        grbc.ipadx = 3; grbc.ipady = 1;
        this.add(panel1,grbc);
                
        TxtFld = new TextField(); 
        
        grbc.gridx=0;grbc.gridy=1;
        grbc.gridheight = 1; grbc.weightx = 0.5; grbc.weighty = 0;
        grbc.insets = new Insets(5,5,5,5);
        this.add(TxtFld,grbc);
        
        TxtArea = new TextArea();
        grbc.gridx=0;grbc.gridy=2;
        grbc.gridheight = 1; grbc.weightx = 0.5; grbc.weighty = 0.7;
        grbc.insets = new Insets(5,5,8,5);
        this.add(TxtArea,grbc);
        
        
    }

String filepath;
boolean k=false;
 int countchar = 0,countwords=0,countspace=0,lekseis_me_kena=0;
       @Override
    public void actionPerformed(ActionEvent ae) {
       String ButtonPressed=ae.getActionCommand();
        
        if(ButtonPressed.equals("Clear"))
                clearscreen();
        if(ButtonPressed.equals("Open"))
                showchooser();
        if(ButtonPressed.equals("Save"))            
                getsave();
        if(ButtonPressed.equals("Copy"))         
                getcopy();      
        if(ButtonPressed.equals("close"))
                 System.exit(0);        
        if(ButtonPressed.equals("open"))                 
                 showchooser();        
        if(ButtonPressed.equals("save"))
                getsave();       
        if(ButtonPressed.equals("clear"))       
               clearscreen(); 
        if(ButtonPressed.equals("copy"))
               getcopy();
        if(ButtonPressed.equals("statistics"))
        {      k=true;
               getstatistics(k);
        }
    }         

    private void clearscreen() {
        TxtFld.setText(" ");
        TxtArea.setText(" ");
    }

    private void getcopy()   {
        
            
          if(TxtFld.getText().equals(""))
          {
              JOptionPane.showMessageDialog(this, "open a file first and then copy to it");
              return;
          }
               
              try {
           FileReader file = new FileReader(filepath);
           String newfile=JOptionPane.showInputDialog("please enter the name of the file: ");
        //   if(JOptionPane.CANCEL_OPTION)
           //    return;
           if(newfile.isEmpty())
               return;
           String curFileExt = TxtFld.getText().substring(TxtFld.getText().lastIndexOf("."),TxtFld.getText().length() );
      
           while(!newfile.endsWith(".txt") && !newfile.contains(".txt"))
           {
				if(newfile.equals(".txt"))
					newfile = JOptionPane.showInputDialog(this,"Invalid FileName Enter Again\nMake sure extension is .txt"); 
				else
					newfile= JOptionPane.showInputDialog(this,"Invalid FileName Enter Again\nMake sure extension is .txt ");
           }
             int lengthfile=newfile.length();
             int lengthpath=TxtFld.getText().length();
             
            //pos na bro to monopati to0 neo0 arxeiou aferontas to onoma to0 paliou??
             
            String currfile=filepath+newfile+curFileExt;
        //   String currfile=TxtFld.getText().substring(0,lengthpath-lengthfile); edo prepei na paro to onoma mono to0 neou arxeiou
             FileOutputStream nfile = new FileOutputStream(currfile);
       
            int count = 0;
            char input;
            
            while (file.ready()) {
                input = (char) file.read();
                nfile.write(input);
                
                count++;
            }
            
      
            file.close();
            
        } catch(IOException e) {
            System.out.println("Error " + e.toString());
        }  
    } 

    private void getsave() {
        
		   if(TxtFld.getText().equals("")){
			JOptionPane.showMessageDialog(this, "There is nothing to Save.\nOpen a file first", "",JOptionPane.INFORMATION_MESSAGE, null); 
		   return ;}
			try {
				
				FileOutputStream file = new FileOutputStream(TxtFld.getText());
				BufferedOutputStream buff = new BufferedOutputStream(file);
				DataOutputStream res = new DataOutputStream(buff);
				String buf=TxtArea.getText();      
			   
				res.writeBytes(buf);
				res.close();
			} catch (FileNotFoundException ex) {
				Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
			}
            
    }

    private void showchooser() {
         JFileChooser chooser=new JFileChooser();
            int returnvalue=chooser.showOpenDialog(this);
            if(returnvalue==JFileChooser.APPROVE_OPTION)
            {   try {
					filepath=chooser.getSelectedFile().getAbsolutePath();
					TxtFld.setText(filepath);
			 
					FileReader file ;
					file = new FileReader(filepath);              
			   
					char input;
					String buf="" ;
					int count_paragraph=0;
					while (file.ready()) {
						input = (char) file.read();
						countchar++;
						if(input==' ')
						{
							countwords++;
							countspace++;
						}
						 if(input=='\n')  
							 count_paragraph++;
						buf+=input;
						

					}
					 countwords++;
					
					lekseis_me_kena=countwords+countspace;
			   //     System.out.println("word count " + countwords);
				  //  System.out.println("character count " + countchar);
				  //  System.out.println("count of the words + spaces: " + lekseis_me_kena);
				   int size=lekseis_me_kena;
					TxtArea.setText(buf);
				} catch (FileNotFoundException ex) {
					Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
				}
	
		   }else  if(returnvalue==JFileChooser.CANCEL_OPTION)
			   return;
       
          }    

    private void getstatistics(boolean k) {
        if(TxtFld.getText().equals("")){
             JOptionPane.showMessageDialog(this, "There is no statistics.\nOpen a file first", "",JOptionPane.INFORMATION_MESSAGE, null); return;
        }
        sec_window s=new sec_window( countwords, countchar,lekseis_me_kena);
        s.setSize(new Dimension(200,200));
        s.setTitle("statistics");
        s.setLocationRelativeTo(null);
        s.setVisible(k);
    }}    
       
    
       
             
          
     
 
    

 
                
            
    
    
    
    
    
