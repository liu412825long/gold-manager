package cn.sh.util.page;

import java.util.ArrayList;
import java.util.List;

public class PageUtilTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		list.add("str1");
		list.add("str2");
		list.add("str3");
		list.add("str4");
		list.add("str5");
		list.add("str6");
		list.add("str7");
		list.add("str8");
		list.add("str9");
		list.add("str10");
		list.add("str11");
		list.add("str12");
		list.add("str13");
		list.add("str14");
		list.add("str15");
		list.add("str16");
		list.add("str17");
		list.add("str18");
		list.add("str19");
		list.add("str20");
		list.add("str21");
		PageUtil<String> page=new PageUtil<String>();
		page.setCurrentPage(2);
		page.setPageSize(20);
		page.setRecordTotal(45);
		page.setData(list);
		System.out.println(page);

	}

}
