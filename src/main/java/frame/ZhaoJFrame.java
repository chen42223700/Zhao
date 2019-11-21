package frame;

import entity.ProductDetail;
import entity.ProductEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import util.ZhaoExcelUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class ZhaoJFrame {

    private static int PAGE_SIZE = 15;

    public ZhaoJFrame(){
        JFrame jf = new JFrame("Excel解析");
        jf.setSize(330, 270);
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
        //注册按钮事件
        btnFile.addActionListener((actionEvent) -> {
                    JFileChooser fc = new JFileChooser();
                    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);//只能选择目录
                    int flag = -1;

                    try {
                        flag = fc.showOpenDialog(null);
                    } catch (HeadlessException head) {
                        System.out.println("Open File Dialog ERROR!");
                    }
                    if (flag == JFileChooser.APPROVE_OPTION) {
                        //获得该文件
                        textFile.setText(fc.getSelectedFile().getPath());
                    }
                }
        );
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

        JLabel JLOffset=new JLabel("偏距:");
        panel.add(JLOffset);
        JTextField textOffset = new JTextField(24);
        panel.add(textOffset);

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

        btnQuery.addActionListener((actionEvent) ->{
                String width = textWidth.getText();
                String diameter = textDiameter.getText();
                String offset = textOffset.getText();
                String screw = textScrew.getText();
                String distribution = textDistribution.getText();
                String centre = textCentre.getText();
                String filePath = textFile.getText();

                //如果全为空报错
                if(StringUtils.isBlank(width)
                        && StringUtils.isBlank(diameter)
                        && StringUtils.isBlank(offset)
                        && StringUtils.isBlank(screw)
                        && StringUtils.isBlank(distribution)
                        && StringUtils.isBlank(centre)){
                    JOptionPane.showMessageDialog(jf,"至少输入一个条件","错误",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ProductEntity entity;
                try {
                    entity = ZhaoExcelUtil.processExcelData(filePath, width, diameter, offset, screw, distribution, centre, 1, PAGE_SIZE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(jf,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //获取详细数据
                List<ProductDetail> productDetails = entity.getProductDetails();

                if (CollectionUtils.isEmpty(productDetails)){
                    JOptionPane.showMessageDialog(jf,"没有匹配数据","提示",JOptionPane.PLAIN_MESSAGE);
                    return;
                }

                //展示结果
                JFrame jfResult = new JFrame("匹配数据");
                jfResult.setSize(1800, 400);
                jfResult.setLocationRelativeTo(null);

                // 创建内容面板，使用边界布局
                JPanel jpResult = new JPanel(new BorderLayout());

                // 表格头数据
                String[] columnNames = {"行号", "生产基地", "产品名称", "零件号", "宽度",
                        "直径", "偏距", "中心孔直径", "螺孔数", "分布圆",
                        "单轮载荷", "轮辋材料", "轮辋厚度", "轮辐材料",
                        "轮辐厚度", "弯曲疲劳试验", "弯曲试验结果", "径向疲劳试验", "径向试验结果",
                        "轮辐模具", "轮辋模具", "压配模具", "产品数模", "产品图纸"};
                Object[][] data = new Object[productDetails.size()][columnNames.length];
                for(int i=0;i<productDetails.size();i++){
                    data[i][0]=productDetails.get(i).getRowNum();
                    data[i][1]=productDetails.get(i).getBase();
                    data[i][2]=productDetails.get(i).getProductName();
                    data[i][3]=productDetails.get(i).getComponentNum();
                    data[i][4]=productDetails.get(i).getWidth();
                    data[i][5]=productDetails.get(i).getDiameter();
                    data[i][6]=productDetails.get(i).getOffset();
                    data[i][7]=productDetails.get(i).getCentre();
                    data[i][8]=productDetails.get(i).getScrew();
                    data[i][9]=productDetails.get(i).getDistribution();
                    data[i][10]=productDetails.get(i).getSingleWheelLoad();
                    data[i][11]=productDetails.get(i).getRiMaterial();
                    data[i][12]=productDetails.get(i).getRimthickness();
                    data[i][13]=productDetails.get(i).getSpokeMaterial();
                    data[i][14]=productDetails.get(i).getSpokeThicknes();
                    data[i][15]=productDetails.get(i).getBendingFatigueTest();
                    data[i][16]=productDetails.get(i).getBendingTestResult();
                    data[i][17]=productDetails.get(i).getRadialFatigueTest();
                    data[i][18]=productDetails.get(i).getRadialTestResult();
                    data[i][19]=productDetails.get(i).getSpokeMold();
                    data[i][20]=productDetails.get(i).getRiMould();
                    data[i][21]=productDetails.get(i).getPressingMold();
                    data[i][22]=productDetails.get(i).getProductDigitalModel();
                    data[i][23]=productDetails.get(i).getProductdrawings();
                }


                // 创建一个表格，指定 所有行数据 和 表头
                TableModel model = new DefaultTableModel(data, columnNames);
                JTable table = new JTable(model);


                // 把 表头 添加到容器顶部（使用普通的中间容器添加表格时，表头 和 内容 需要分开添加）
                jpResult.add(table.getTableHeader(), BorderLayout.NORTH);
                // 把 表格内容 添加到容器中心
                jpResult.add(table, BorderLayout.CENTER);

                // 创建按钮
                JPanel panelBtn = new JPanel();
                panelBtn.setLayout(null);
                JButton btnLastPage = new JButton("上一页");
                btnLastPage.setBounds(750,50, 80, 30);
                JButton btnNextPage = new JButton("下一页");
                btnNextPage.setBounds(950,50, 80, 30);
                JLabel JLPagelabel=new JLabel("页码:");
                JLPagelabel.setBounds(850,50, 30, 30);

                JLabel JLPageNum=new JLabel(entity.getPageNum() + "/" + entity.getTotalPage());
                JLPageNum.setBounds(890,50, 30, 30);

                //上一页
                btnLastPage.addActionListener((actionBtnLastPage) -> {
                        int currentPage = Integer.parseInt(JLPageNum.getText().substring(0,JLPageNum.getText().indexOf("/")));

                        if (currentPage == 1){
                            JOptionPane.showMessageDialog(jfResult,"已经是第一页","错误",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        btnProcess(--currentPage, filePath, width, diameter, offset, screw, distribution, centre, jfResult, table, JLPageNum);
                });
                //下一页
                btnNextPage.addActionListener((actionBtnNextPage) -> {
                        int currentPage = Integer.parseInt(JLPageNum.getText().substring(0,JLPageNum.getText().indexOf("/")));
                        int totalPage = Integer.parseInt(JLPageNum.getText().substring(JLPageNum.getText().indexOf("/") + 1));
                        if (currentPage == totalPage){
                            JOptionPane.showMessageDialog(jfResult,"已经是最后一页","错误",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        btnProcess(++currentPage, filePath, width, diameter, offset, screw, distribution, centre, jfResult, table, JLPageNum);
                });

                panelBtn.add(btnLastPage);
                panelBtn.add(btnNextPage);
                panelBtn.add(JLPagelabel);
                panelBtn.add(JLPageNum);

                jfResult.add(jpResult, BorderLayout.NORTH);
                jfResult.add(panelBtn);
                jfResult.setLocationRelativeTo(null);
                jfResult.setVisible(true);
            }
        );

        panel.add(btnQuery);

        // 显示窗口
        jf.setContentPane(panel);
        jf.setVisible(true);
    }

    /**
     *
     * @param pageNum 页码
     * @param filePath 文件路径
     * @param width 宽度
     * @param diameter 直径
     * @param screw 螺孔数
     * @param distribution 分布圆
     * @param centre 中心孔
     * @param frame frame
     * @param table table
     * @param JLPageNum lable
     */
    private static void btnProcess(int pageNum,
                                   String filePath,
                                   String width,
                                   String diameter,
                                   String offset,
                                   String screw,
                                   String distribution,
                                   String centre,
                                   JFrame frame,
                                   JTable table,
                                   JLabel JLPageNum){

        ProductEntity entity;
        try {
            entity = ZhaoExcelUtil.processExcelData(filePath, width, diameter, offset, screw, distribution, centre, pageNum, PAGE_SIZE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame,e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            frame.dispose();
            return;
        }
        //获取详细数据
        List<ProductDetail> productDetails = entity.getProductDetails();

        DefaultTableModel tableModel = ((DefaultTableModel) table.getModel());
        tableModel.setRowCount(0);
        for (ProductDetail detail:productDetails) {
            tableModel.addRow(new Object[]{
                    detail.getRowNum(),
                    detail.getBase(),
                    detail.getProductName(),
                    detail.getComponentNum(),
                    detail.getWidth(),
                    detail.getDiameter(),
                    detail.getOffset(),
                    detail.getCentre(),
                    detail.getScrew(),
                    detail.getDistribution(),
                    detail.getSingleWheelLoad(),
                    detail.getRiMaterial(),
                    detail.getRimthickness(),
                    detail.getSpokeMaterial(),
                    detail.getSpokeThicknes(),
                    detail.getBendingFatigueTest(),
                    detail.getBendingTestResult(),
                    detail.getRadialFatigueTest(),
                    detail.getRadialTestResult(),
                    detail.getSpokeMold(),
                    detail.getRiMould(),
                    detail.getPressingMold(),
                    detail.getProductDigitalModel(),
                    detail.getProductdrawings()
            });
        }
        //设置页码
        JLPageNum.setText(entity.getPageNum() + "/" + entity.getTotalPage());
    }
}
