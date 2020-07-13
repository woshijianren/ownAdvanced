BigDecimal推荐用new BigDecimal(String)来构造
最重要的一点是：new BigDecimal(String)是没有长度限制的，而BigDecimal(12)这种，他有个长度大小的限制
new BigDecimal("12.00") => 12.00
new BigDecimal(12.00) => 12
new BigDecimal("12.12") => 12.12
new BigDecimal(12.12) =>  (精度损失)12.1199999999999992184029906638897955417633056640625
new BigDecimal("12.00")/new BigDecimal(4.000) => 3.00(这个3有两位小数是因为被除数12.00有两位小数)
new BigDecimal(12.00)/new BigDecimal(4.000) => 3(因为被除数是12而不是12.00，所以是无小数位)
new DecimalFormat(",###.##").format(12345.126d)=>小数位默认是>=6向上转，<6就向下转，结果为12,345.13

切面：
环绕通知的返回值会代替被环绕的方法的返回值
环绕通知需要在被环绕方法的调用放是被注入而不是自己new出来的时候才有用，因此
@Autowired
private MoneyData moneyData;

public void parseTransfer() {
    × moneyData = new MoneyData; // 错误的，因为又被new 了，要想下面这样写
    √ MoneyData moneyDataNew = new MoneyData("", "", "");
    // 调用hutool的BeanUtil.copyProperties方法
    moneyData.copy(moneyDataNew); // BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    moneyData.xx(); // 调用方法
    // 像上面第二种那样就不是new出来的了，而是赋值
}
