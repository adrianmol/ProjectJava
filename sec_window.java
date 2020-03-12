/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava;

import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

/**
 *
 * @author Jhon
 */
public class sec_window extends JFrame {

    sec_window(int countwords, int countchar, int lekseis_me_kena) {
       Label l1=new Label("no_of_words: "+ countwords);
       Label l2=new Label("no_of_characters: "+ countchar);
       Label l3=new Label("no_of_words+spaces: "+ lekseis_me_kena);
       FlowLayout fl=new FlowLayout();
       this.setLayout(fl);
       this.add(l1);
       this.add(l2);
       this.add(l3);
    }

   
    
}
