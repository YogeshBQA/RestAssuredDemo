package api.utilities;

import org.testng.annotations.DataProvider;


import java.io.IOException;

public class DataProviders {
    @DataProvider(name="Data")
    public Object[][] getAllData() throws IOException {
       // String path= "C:\Users\Yogesh Badhe\Desktop\WorkSpace_Yogesh\testData"+"//UserData.xlsx";
String path=System.getProperty("user.dir")+"//testData//UserData.xlsx";
XLUtility xl= new XLUtility(path);
int rownum=xl.getRowCount("Sheet1");
int colCount=xl.getCellCount("Sheet1",1);

String apiData[][]=new String[rownum][colCount];

for(int i=1;i<rownum;i++)
{
    for(int j=1;j<colCount;j++)
    {
apiData[i-1][j]=xl.getCellData("Sheet1",i,j);
    }

}
return apiData;
    }


    @DataProvider(name="UserNames")
    public Object[] getUserName() throws IOException {

        String path=System.getProperty("user.dir")+"//testData//UserData.xlsx";
        XLUtility xl= new XLUtility(path);
        int rownum=xl.getRowCount("Sheet1");


        String apiData[]=new String[rownum];

        for(int i=1;i<rownum;i++)
        {

                apiData[i-1]=xl.getCellData("Sheet1",i,1);


        }
        return apiData;
    }

}
