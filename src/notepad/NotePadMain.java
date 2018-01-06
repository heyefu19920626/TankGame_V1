package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Description:
 * 简单记事本开发
 * @author heyefu
 * Create in: 2018-01-05
 * Time: 下午8:47
 **/
public class NotePadMain extends JFrame implements ActionListener{

//    中间可编辑文本区
    JTextArea jTextArea = null;
    JScrollPane jScrollPane = null;
//    工具条
    JMenuBar jMenuBar = null;
//    文件菜单
    JMenu fileMenu = null;

    JMenuItem openFile = null;
    JMenuItem saveFile = null;

    public NotePadMain(){
        jTextArea = new JTextArea();
        jScrollPane = new JScrollPane(jTextArea);
        jMenuBar = new JMenuBar();
        fileMenu = new JMenu("文件");
        openFile = new JMenuItem("打开");
        saveFile = new JMenuItem("保存");

        this.add(jScrollPane);
        this.setJMenuBar(jMenuBar);
        jMenuBar.add(fileMenu);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);

        openFile.addActionListener(this);
        openFile.setActionCommand("openFile");
        saveFile.addActionListener(this);
        saveFile.setActionCommand("saveFile");


        this.setTitle("记事本");
        this.setBounds(700,300,500,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        new NotePadMain();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("openFile")){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("打开");
            fileChooser.showOpenDialog(null);

            if (fileChooser.getSelectedFile() == null){
                System.out.println("Selected is null!");
                return;
            }
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            File open = new File(filePath);


            FileReader fileRead = null;
            BufferedReader buffRead = null;
            String textContent;
            String textAll = "";

            try {
                fileRead = new FileReader(open);
                buffRead = new BufferedReader(fileRead);
                while ((textContent = buffRead.readLine()) != null){
                    textAll += textContent + "\r\n";
                }
                jTextArea.setText(textAll);
            } catch (FileNotFoundException e1) {
                System.out.println(filePath + " not exist!");
                e1.printStackTrace();
            } catch (IOException e1) {
                System.out.println(filePath + " read occurred wrong!");
                e1.printStackTrace();
            }finally {
                try {
                    fileRead.close();
                    buffRead.close();
                } catch (IOException e1) {
                    System.out.println("文件没有正常关闭!");
                    e1.printStackTrace();
                }
                System.out.println("Open Successful!");
            }
        } else if (e.getActionCommand().equals("saveFile")){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("保存");
            fileChooser.showSaveDialog(null);

            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            File write = new File(filePath);

            FileWriter fileWrit = null;
            BufferedWriter bufferWrite = null;

            String textWrite = jTextArea.getText();

            try {
                fileWrit = new FileWriter(write);
                bufferWrite = new BufferedWriter(fileWrit);
                int len = textWrite.length();
                bufferWrite.write(textWrite,0,len);
            } catch (IOException e1) {
                System.out.println("Open write occurred wrong!");
                e1.printStackTrace();
            }finally {
                try {
//                    关闭文件流先后顺序
                    bufferWrite.close();
                    fileWrit.close();
                } catch (IOException e1) {
                    System.out.println("Close write occurred wrong!");
                    e1.printStackTrace();
                }
                System.out.println("Save Successful!");
            }

        }
    }
}
