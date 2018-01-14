package TankGame_V1;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * Description:
 * 记录游戏类,用来保存游戏
 *
 * @author heyefu
 * Create in: 2018-01-14
 * Time: 下午5:21
 **/
public class RecordGame {


    /**
     * Description: 项目根目录
     */
    private static String rootPath = System.getProperty("user.dir");
    /**
     * Description: 存档目录
     */
    private static String savePath = rootPath + File.separator + "src" + File.separator + "archive";


    /**
     * Description:
     * 保存存档
     *
     * @param myTank       自己坦克
     * @param killEnemy    杀死敌人坦克数量
     * @param residueEnemy 剩余敌人坦克数量
     * @param enemyTanks   敌人坦克
     * @return void
     * @author heyefu 下午8:43 18-1-14
     **/
    public static void saveGame(MyTank myTank, int killEnemy, int residueEnemy, Vector<EnemyTank> enemyTanks) {

//        存档第一行是自己坦克的坐标及方向
        String myTankCoordinate = String.valueOf(myTank.x) + " " + Integer.toString(myTank.y) + " " + Integer.toString(myTank.direction);
//        存档第二行是击杀敌方坦克数量与剩余敌方坦克,以空格隔开
        String killAndResidue = String.valueOf(killEnemy) + " " + String.valueOf(residueEnemy);
//        存档位置
        File archive = new File(savePath + File.separator + "archive");

        FileWriter fwrite = null;
        BufferedWriter buffWrite = null;

        try {
            fwrite = new FileWriter(archive);
            buffWrite = new BufferedWriter(fwrite);

            buffWrite.write(myTankCoordinate);
//            换行
            buffWrite.newLine();
            buffWrite.write(killAndResidue);
            buffWrite.newLine();

//            保存所有坦克的坐标和方向
            for (int i = 0; i < enemyTanks.size(); i++) {
                int x = enemyTanks.get(i).getX();
                int y = enemyTanks.get(i).getY();
                int direction = enemyTanks.get(i).getDirection();

                String ememyTank = String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(direction);
                buffWrite.write(ememyTank);
                buffWrite.newLine();
            }

            buffWrite.flush();
            System.out.println("保存成功!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (buffWrite != null) {
                try {
                    buffWrite.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fwrite != null) {
                try {
                    fwrite.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    /**
     * Description:
     * 读取存档
     *
     * @param
     * @return java.util.HashMap<java.lang.String       ,       java.util.ArrayList       <       java.lang.String>>
     * @author heyefu 下午8:44 18-1-14
     **/
    public static HashMap<String, ArrayList<String>> readRecord() {

        File archive = new File(savePath + File.separator + "archive");

        FileReader fRead = null;
        BufferedReader buffRead = null;
        int killEnemy = 0;
        int residueEnemy = 0;
        String myTankCoordinate = "";
        String killAndResidue = "";

        HashMap<String, ArrayList<String>> recordMap = new HashMap<String, ArrayList<String>>();
        try {
            fRead = new FileReader(archive);
            buffRead = new BufferedReader(fRead);
//            读取自己坦克的坐标及方向
            myTankCoordinate = buffRead.readLine();
            String myTank[] = myTankCoordinate.split(" ");
            ArrayList<String> recordMyTank = new ArrayList<String>();
            recordMyTank.add(myTank[0]);
            recordMyTank.add(myTank[1]);
            recordMyTank.add(myTank[2]);
            recordMap.put("myTank", recordMyTank);

//            读取杀死敌人坦克数量及剩余坦克数量
            killAndResidue = buffRead.readLine();
            String number[] = killAndResidue.split(" ");
            ArrayList<String> recordNumber = new ArrayList<String>();
            recordNumber.add(number[0]);
            recordNumber.add(number[1]);
            recordMap.put("number", recordNumber);

//            读取敌人坦克坐标及方向
            ArrayList<String> recordEnemy = new ArrayList<String>();
            for (int i = 0; i < Integer.valueOf(number[1]); i++) {
                String enemy = buffRead.readLine();
                recordEnemy.add(enemy);
            }
            recordMap.put("enemy", recordEnemy);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (buffRead != null) {
                try {
                    buffRead.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fRead != null) {
                try {
                    fRead.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return recordMap;
    }

}
