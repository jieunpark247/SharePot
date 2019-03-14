package floatingbanner;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MainDAO {

   static MainDTO dustDTO;
   private final static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";

   public static MainDTO dustView() {

	   String url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?serviceKey=bCmoAhi3OlijIFedb0McSQztB7s1AkW4KaM19P%2BGfs%2FwQ30Dk7T92JRsz92pVd%2BHBML8LzLWsYbAJ7vlLsTTfw%3D%3D&numOfRows=10&pageNo=1&stationName=%EC%84%9C%EC%B4%88%EA%B5%AC&dataTerm=DAILY&ver=1.3";      // 1. URL ����

      dustDTO = new MainDTO();
      // 2. HTML ��������
      Connection conn = Jsoup.connect(url).header("Content-Type", "application/json;charset=UTF-8")
            .userAgent(USER_AGENT).method(Connection.Method.GET).ignoreContentType(true);

      Document doc = null;
      try {
         doc = conn.get();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      String[] pm10_array; // �ʹ̼������� split���� ���� ��
      String[] pm25_array; // �ʹ̼������� split���� ���� ��

      String all = doc.toString(); // �Ľ��� ���� string���� �����!

      // �̼����� pm10�� ���ڿ� ����
      pm10_array = all.split("<pm10Value>");// ���� 0~30 ����~80 ����~150 �ſ쳪��151~
      String[] pm10_array_value = new String[6]; // 6���� ����
      String pm10_state;
      for (int i = 0; i <= 1; i++) {
         pm10_array_value = pm10_array[i].split("<");

      }

      pm10_array_value[0] = pm10_array_value[0].trim();// ���� ����

      if (pm10_array_value[0].equals("-")) {
         pm10_state = "���� �Ұ�";
      } else if (Integer.parseInt(pm10_array_value[0]) <= 30) {
         pm10_state = "����";
      } else if (30 < Integer.parseInt(pm10_array_value[0]) && Integer.parseInt(pm10_array_value[0]) <= 80) {
         pm10_state = "����";
      } else if (80 < Integer.parseInt(pm10_array_value[0]) && Integer.parseInt(pm10_array_value[0]) <= 150) {
         pm10_state = "����";
      } else {
         pm10_state = "�ſ� ����";
      }
      // System.out.println(pm10_state);

      // �ʹ̼����� pm25�� ���ڿ� ����
      pm25_array = all.split("<pm25Value>");// ��0~15(����) ��16~35(����) ��36~75(����) ��76~(�ſ� ����)��
      String[] pm25_array_value = new String[6]; // 6���� ����
      String pm25_state;
      for (int i = 0; i <= 1; i++) {
         pm25_array_value = pm25_array[i].split("<");
         String values = pm25_array_value[0];
      }
      pm25_array_value[0] = pm25_array_value[0].trim();// ���� ����
      // System.out.println(pm25_array_value[0]);
      if (pm25_array_value[0].equals("-")) {
         pm25_state = "���� �Ұ�";
      } else if (Integer.parseInt(pm25_array_value[0]) <= 15) {
         pm25_state = "����";
      } else if (15 < Integer.parseInt(pm25_array_value[0]) && Integer.parseInt(pm25_array_value[0]) <= 35) {
         pm25_state = "����";
      } else if (35 < Integer.parseInt(pm25_array_value[0]) && Integer.parseInt(pm25_array_value[0]) <= 75) {
         pm25_state = "����";
      } else {
         pm25_state = "�ſ� ����";
      }
      // System.out.println(pm25_state);

      // http://www.kma.go.kr/wid/queryDFS.jsp?gridx=37.486809&gridy=126.801890 ���
      String url_weather = "http://www.kma.go.kr/wid/queryDFS.jsp?gridx=37.486809&gridy=126.801890";
      // 1. URL ����

      // 2. HTML ��������
      Connection conn_weather = Jsoup.connect(url_weather).header("Content-Type", "application/json;charset=UTF-8")
            .userAgent(USER_AGENT).method(Connection.Method.GET).ignoreContentType(true);

      Document doc_weather = null;
      try {
         doc_weather = conn_weather.get();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      String[] weather_clock_array; // ���� ���� �ð� split���� ���� ��
      String[] weather_degree_array; // �µ��� split���� ���� ��
      String[] weather_state_array; // ���� ���� ���� split���� ���� ��
      String[] weather_flag_array; // ���� ���� ����
      String[] weather_rain_percent_array;// ���� ���� %
      String[] weather_max_array;// �ְ� ��
      String[] weather_min_array;// ���� ��

      String weather = doc_weather.toString(); // �Ľ��� ���� string���� �����!

      // ������ �ð��뺰�� �����´� �� �ð��븦 ������ �ִ�.
      weather_clock_array = weather.split("<hour>");// 3�ð� ������ �޾ƿ´�
      String[] weather_clock_array_value = new String[7]; // 6���� ��������
      
      for (int i = 0; i <= 6; i++) {
         String[] weather_clock_tem_array_value = new String[2];
         weather_clock_tem_array_value = weather_clock_array[i].split("<");
         weather_clock_array_value[i] = weather_clock_tem_array_value[0].trim();
         // System.out.println(weather_clock_array_value[i]);
      }

      weather_degree_array = weather.split("<temp>");// ���� �µ��� �޾ƿ´�
      String[] weather_degree_array_value = new String[7]; // 6���� ��������
      for (int i = 0; i <= 6; i++) {
         String[] weather_degree_tem_array_value = new String[2];
         weather_degree_tem_array_value = weather_degree_array[i].split("<");
         weather_degree_array_value[i] = weather_degree_tem_array_value[0].trim();
         // System.out.println("�̰�="+weather_degree_array_value[i]);
      }

      weather_state_array = weather.split("<pty>");// ���� ���� ���¸� �޾ƿ´�
      String[] weather_state_array_value = new String[7]; // 6���� ��������
      for (int i = 0; i <= 6; i++) {
         String[] weather_state_tem_array_value = new String[2];
         weather_state_tem_array_value = weather_state_array[i].split("<");
         weather_state_array_value[i] = weather_state_tem_array_value[0].trim();

         switch (weather_state_array_value[i]) {

         case "0":
            weather_state_array_value[i] = "����";
            break;
         case "1":
            weather_state_array_value[i] = "��";
            break;
         case "2":
         case "3":
            weather_state_array_value[i] = "��������";
            break;
         default:
            weather_state_array_value[i] = "��";
            break;
         }

      }

      weather_flag_array = weather.split("<wfKor>");// ���� ���� ���� ���¸� �޾ƿ´�
      String[] weather_flag_array_value = new String[7]; // 6���� ��������
      for (int i = 0; i <= 6; i++) {
         String[] weather_state_tem_array_value = new String[2];
         weather_state_tem_array_value = weather_flag_array[i].split("<");
         weather_flag_array_value[i] = weather_state_tem_array_value[0].trim();

      }
      weather_rain_percent_array = weather.split("<pop>");// ���� ���� Ȯ�� ���� ���¸� �޾ƿ´�
      String[] weather_rain_percent_array_value = new String[7]; // 6���� ��������
      for (int i = 0; i <= 6; i++) {
         String[] weather_state_tem_array_value = new String[2];
         weather_state_tem_array_value = weather_rain_percent_array[i].split("<");
         weather_rain_percent_array[i] = weather_state_tem_array_value[0].trim();

      }

      weather_max_array = weather.split("<tmx>");// ���� ���� Ȯ�� ���� ���¸� �޾ƿ´�
      String[] weather_max_array_value = new String[7]; // 6���� ��������
      for (int i = 0; i <= 6; i++) {
         String[] weather_state_tem_array_value = new String[2];
         weather_state_tem_array_value = weather_max_array[i].split("<");
         weather_max_array[i] = weather_state_tem_array_value[0].trim();
      }

      weather_min_array = weather.split("<tmn>");// ���� ���� Ȯ�� ���� ���¸� �޾ƿ´�
      String[] weather_min_array_value = new String[7]; // 6���� ��������
      for (int i = 0; i <= 6; i++) {
         String[] weather_state_tem_array_value = new String[2];
         weather_state_tem_array_value = weather_min_array[i].split("<");
         weather_min_array[i] = weather_state_tem_array_value[0].trim();

      }

      // http://www.kma.go.kr/wid/queryDFS.jsp?gridx=37.3876442&gridy=127.0988379 �д�
      dustDTO.setPm10_array(pm10_array_value[0]);
      dustDTO.setPm10_state(pm10_state);
      dustDTO.setPm25_array(pm25_array_value[0]);
      dustDTO.setPm25_state(pm25_state);
      dustDTO.setWeather_clock_array(weather_clock_array_value[1]);
      dustDTO.setWeather_degree_array(weather_degree_array_value[1]);
      dustDTO.setWeather_state_array(weather_state_array_value[1]);
      dustDTO.setWeather_flage_array(weather_flag_array_value[1]);
      dustDTO.setWeather_rain_percent_array(weather_rain_percent_array[1]);

      for (int i = 1; weather_min_array.length > i; i++) {
         //System.out.println(i + "cccc" + weather_min_array[i]);
         if (weather_min_array[i].equals("-999.0") || weather_min_array[i] == null || weather_min_array[i].equals("")) {
            //System.out.println("Min �ٽ�" + weather_min_array[i]);
         } else {
            dustDTO.setWeather_min_array(weather_min_array[i]);
            //System.out.println(weather_min_array[i]);
            break;
         }
      }
      for (int i = 1; weather_max_array.length > i; i++) {
         //System.out.println(i + "ddd" + weather_min_array[i]);
         if (weather_max_array[i].equals("-999.0") || weather_max_array[i] == null || weather_max_array[i].equals("")) {
            //System.out.println("Max �ٽ�" + weather_max_array[i]);
         } else {
            dustDTO.setWeather_max_array(weather_max_array[i]);
            //System.out.println(i);
            break;
         }
      }

      return dustDTO;

   }
}