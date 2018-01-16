package eg.edu.alexu.csd.datastructure.iceHockey.cs48;

public class main {

	public static void main(String[] args) {
		String [] phot=new String[15];
		phot[0]="8D88888J8L8E888";
		phot[1]="88NKMG8N8E8JI88";
		phot[2]="888NS8EU88HN8EO";
		phot[3]="LUQ888A8TH8OIH8";
		phot[4]="888QJ88R8SG88TY";
		phot[5]="88ZQV88B88OUZ8O";
		phot[6]="FQ88WF8Q8GG88B8";
		phot[7]="8S888HGSB8FT8S8";
		phot[8]="8MX88D88888T8K8";
		phot[9]="8S8A88MGVDG8XK8";
		phot[10]="M88S8B8I8M88J8N";
		phot[11]="8W88X88ZT8KA8I8";
		phot[12]="88SQGB8I8J88W88";
		phot[13]="U88H8NI8CZB88B8";
		phot[14]="8PK8H8T8888TQR8";
		Playerfind omar=new Playerfind();
		omar.findPlayers(phot, 8, 9);
		omar.findPlayers(phot, 0, 9);
		
	}

}
