package floatingbanner;

public class MainDTO {

	String pm10_array; // �̼����� ���� ��
	String pm25_array; // �ʹ̼����� ���� ��

	String pm10_state; // �̼����� ���� ��
	String pm25_state; // �ʹ̼����� ���� ��

	String weather_clock_array; // ���� ���� �ð� split���� ���� ��
	String weather_degree_array; // �µ��� split���� ���� ��
	String weather_state_array;
	String weather_flage_array; // ���� ���� ������ �����´�
	String weather_rain_percent_array; // ���� Ȯ��
	String weather_max_array; // ����
	String weather_min_array; // �ְ�

	public String getWeather_max_array() {
		return weather_max_array;
	}

	public void setWeather_max_array(String weather_max_array) {
		this.weather_max_array = weather_max_array;
	}

	public String getWeather_min_array() {
		return weather_min_array;
	}

	public void setWeather_min_array(String weather_min_array) {
		this.weather_min_array = weather_min_array;
	}

	public String getWeather_rain_percent_array() {
		return weather_rain_percent_array;
	}

	public void setWeather_rain_percent_array(String weather_rain_percent_array) {
		this.weather_rain_percent_array = weather_rain_percent_array;
	}

	public String getWeather_flage_array() {
		return weather_flage_array;
	}

	public void setWeather_flage_array(String weather_flage_array) {
		this.weather_flage_array = weather_flage_array;
	}

	public String getPm10_array() {
		return pm10_array;
	}

	public void setPm10_array(String pm10_array) {
		this.pm10_array = pm10_array;
	}

	public String getPm25_array() {
		return pm25_array;
	}

	public void setPm25_array(String pm25_array) {
		this.pm25_array = pm25_array;
	}

	public String getPm10_state() {
		return pm10_state;
	}

	public void setPm10_state(String pm10_state) {
		this.pm10_state = pm10_state;
	}

	public String getPm25_state() {
		return pm25_state;
	}

	public void setPm25_state(String pm25_state) {
		this.pm25_state = pm25_state;
	}

	public String getWeather_clock_array() {
		return weather_clock_array;
	}

	public void setWeather_clock_array(String weather_clock_array) {
		this.weather_clock_array = weather_clock_array;
	}

	public String getWeather_degree_array() {
		return weather_degree_array;
	}

	public void setWeather_degree_array(String weather_degree_array) {
		this.weather_degree_array = weather_degree_array;
	}

	public String getWeather_state_array() {
		return weather_state_array;
	}

	public void setWeather_state_array(String weather_state_array) {
		this.weather_state_array = weather_state_array;
	}

}