package frame;

import entity.ProductEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import util.ZhaoExcelUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ZhaoJFrame {

    public ZhaoJFrame(){
        JFrame jf = new JFrame("Excel解析");
        jf.setSize(330, 250);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 创建内容面板，指定布局为 null，则使用绝对布局
        JPanel panel = new JPanel();

        //文件路径
        JLabel JLFile=new JLabel("文件路径:");
        panel.add(JLFile);

        JTextField textFile = new JTextField(16);
        panel.add(textFile);

        // 创建按钮
        JButton btnFile = new JButton("选择");
        // 设置按钮的宽高
        btnFile.setSize(20, 15);
        btnFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc=new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);//只能选择目录
                String path=null;
                File f=null;
                int flag = -1;
                try{
                    flag=fc.showOpenDialog(null);
                }
                catch(HeadlessException head){
                    System.out.println("Open File Dialog ERROR!");
                }
                if(flag==JFileChooser.APPROVE_OPTION){
                    //获得该文件
//                    f=fc.getSelectedFile().getPath();
                    path=fc.getSelectedFile().getPath();
                    textFile.setText(path);
                }
            }
        });
        panel.add(btnFile);


        //标签
        JLabel JLWidth=new JLabel("宽度:");
        panel.add(JLWidth);

        JTextField textWidth = new JTextField(24);
        panel.add(textWidth);

        JLabel JLDiameter=new JLabel("直径:");
        panel.add(JLDiameter);

        JTextField textDiameter = new JTextField(24);
        panel.add(textDiameter);

        JLabel JLScrew=new JLabel("螺孔数:");
        panel.add(JLScrew);
        JTextField textScrew = new JTextField(23);
        panel.add(textScrew);

        JLabel JLDistribution=new JLabel("分布圆:");
        panel.add(JLDistribution);
        JTextField textDistribution = new JTextField(23);
        panel.add(textDistribution);

        JLabel JLCentre=new JLabel("中心孔:");
        panel.add(JLCentre);
        JTextField textCentre = new JTextField(23);
        panel.add(textCentre);

        // 创建按钮
        JButton btnQuery = new JButton("查询");
        // 设置按钮的坐标
        btnQuery.setLocation(100, 200);
        // 设置按钮的宽高
        btnQuery.setSize(100, 20);

        btnQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String width = textWidth.getText();
                String diameter = textDiameter.getText();
                String screw = textScrew.getText();
                String distribution = textDistribution.getText();
                String centre = textCentre.getText();
                String filePath = textFile.getText();

                //如果全为空报错
                if(StringUtils.isBlank(width)
                        && StringUtils.isBlank(diameter)
                        && StringUtils.isBlank(screw)
                        && StringUtils.isBlank(distribution)
                        && StringUtils.isBlank(centre)){
                    JOptionPane.showMessageDialog(jf,"至少输入一个条件","错误",0);
                    return;
                }
                List<ProductEntity> data = new ArrayList<>();
                try {
                    data = ZhaoExcelUtil.processExcelData(filePath, width, diameter, screw, distribution, centre, 1, 15);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(jf,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (CollectionUtils.isEmpty(data)){
                    JOptionPane.showMessageDialog(jf,"没有匹配数据","提示",JOptionPane.PLAIN_MESSAGE);
                    return;
                }

//                JOptionPane.showMessageDialog(jf,data.size(),"提示",JOptionPane.PLAIN_MESSAGE);
                new InnerTableFrame(data);
            }


        });
        panel.add(btnQuery);

        // 显示窗口
        jf.setContentPane(panel);
        jf.setVisible(true);
    }

    class InnerTableFrame{
        public InnerTableFrame(List<ProductEntity> productEntities) {
            JFrame jf = new JFrame("匹配数据");
            jf.setSize(1800, 400);
            jf.setLocationRelativeTo(null);

            // 创建内容面板，使用边界布局
            JPanel panel = new JPanel(new BorderLayout());

            // 表格头数据
            String[] columnNames = {"行号", "生产基地", "产品名称", "零件号", "宽度",
                    "直径", "偏距", "中心孔直径", "螺孔数", "分布圆",
                    "单轮载荷", "轮辋材料", "轮辋厚度", "轮辐材料",
                    "轮辐厚度", "弯曲疲劳试验", "弯曲试验结果", "径向疲劳试验", "径向试验结果",
                    "轮辐模具", "轮辋模具", "压配模具", "产品数模", "产品图纸"};
            Object[][] data = new Object[productEntities.size()][columnNames.length];
            for(int i=0;i<productEntities.size();i++){
                data[i][0]=productEntities.get(i).getRowNum();
                data[i][1]=productEntities.get(i).getBase();
                data[i][2]=productEntities.get(i).getProductName();
                data[i][3]=productEntities.get(i).getComponentNum();
                data[i][4]=productEntities.get(i).getWidth();
                data[i][5]=productEntities.get(i).getDiameter();
                data[i][6]=productEntities.get(i).getOffset();
                data[i][7]=productEntities.get(i).getCentre();
                data[i][8]=productEntities.get(i).getScrew();
                data[i][9]=productEntities.get(i).getDistribution();
                data[i][10]=productEntities.get(i).getSingleWheelLoad();
                data[i][11]=productEntities.get(i).getRiMaterial();
                data[i][12]=productEntities.get(i).getRimthickness();
                data[i][13]=productEntities.get(i).getSpokeMaterial();
                data[i][14]=productEntities.get(i).getSpokeThicknes();
                data[i][15]=productEntities.get(i).getBendingFatigueTest();
                data[i][16]=productEntities.get(i).getBendingTestResult();
                data[i][17]=productEntities.get(i).getRadialFatigueTest();
                data[i][18]=productEntities.get(i).getRadialTestResult();
                data[i][19]=productEntities.get(i).getSpokeMold();
                data[i][20]=productEntities.get(i).getRiMould();
                data[i][21]=productEntities.get(i).getPressingMold();
                data[i][22]=productEntities.get(i).getProductDigitalModel();
                data[i][23]=productEntities.get(i).getProductdrawings();
            }

            // 创建一个表格，指定 所有行数据 和 表头
            JTable table = new JTable(data, columnNames);

            // 把 表头 添加到容器顶部（使用普通的中间容器添加表格时，表头 和 内容 需要分开添加）
            panel.add(table.getTableHeader(), BorderLayout.NORTH);
            // 把 表格内容 添加到容器中心
            panel.add(table, BorderLayout.CENTER);

            // 创建按钮
            JPanel panelBtn = new JPanel();
            panelBtn.setLayout(null);
            JButton btnLastPage = new JButton("上一页");
            btnLastPage.setBounds(750,50, 80, 30);
            JButton btnNextPage = new JButton("下一页");
            btnNextPage.setBounds(950,50, 80, 30);
            JLabel JLPageNum=new JLabel("页码:");

            panelBtn.add(btnLastPage);
            panelBtn.add(btnNextPage);

            jf.add(panel, BorderLayout.NORTH);
            jf.add(panelBtn);
//            jf.pack();
            jf.setLocationRelativeTo(null);
            jf.setVisible(true);


        }
    }
}
