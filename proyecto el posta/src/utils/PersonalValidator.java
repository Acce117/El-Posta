package utils;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JRadioButton;

import classes.Faculty;
import classes.GeneralState;
import classes.Person;

public final class PersonalValidator 
{
	public static Genre checkSex(JRadioButton male, JRadioButton female) throws Exception{
		Genre sex;
		if(female.isSelected())
			sex = Genre.FEMALE;
		else if(male.isSelected())
			sex = Genre.MALE;
		else
			throw new Exception("El sexo no ha sido seleccionado");
		
		return sex;
	}
	
	private static boolean sameID(String id)
    {
        boolean check = false;

        ArrayList<Person> list = Faculty.getInstance().getPeople();
        if(!list.isEmpty())
        	for(int i = 0; i < list.size() && !check; i++)
        	{
        		check = (id.equals(list.get(i).getId()));
        	}

        return check;
    }
	
	public static void checkID(String id, Genre sex)    
	{
        if(id == null || id.trim().equals(""))
            throw new IllegalArgumentException("ID vacio");
        if(sex == null)
            throw new IllegalArgumentException("Genero vacio");
		if(id.length()!=11)
            throw new IllegalArgumentException("Error en cuanto al tam�o del ID");

        else
        {
            final int MINIMUM_AGE = 15;
            final int MAXIMUM_AGE = 100;
            boolean numberOFLegalChars=true;
            //Se verifica que todos son numeros
            for(int i = 0; i < id.length() && numberOFLegalChars; i++)            
                numberOFLegalChars = (Character.isDigit(id.charAt(i)));
            
            if(!numberOFLegalChars)throw new IllegalArgumentException("Hay una letra en los id");
    
            else
            {
                //Buscando el error en la fecha
    
                //Los dias
                int auxiliarDay = 0;
                auxiliarDay = 10*(id.charAt(4)-'0') + (id.charAt(5)-'0'); //Tomamos los caracteres que corresponden al dia
    
                //Los meses
                int auxiliarMonth = 10*(id.charAt(2)-'0') + (id.charAt(3)-'0');//Tomamos los caracteres que corresponden al mes
    
                if(auxiliarMonth < 1 || auxiliarMonth > 12)
                    throw new IllegalArgumentException("Ese mes no existe");
                
                //Todas estas condiciones son para los dias y los meses.
                //Se usan variables booleanas para acortar el tama�o del if
                boolean januaryToJuly = (auxiliarMonth>=1 && auxiliarMonth <= 7); //si esta entre enero y julio
                boolean february = (auxiliarMonth == 2); //si es el mes de febrero
                boolean augustToDecember = (auxiliarMonth>=8 && auxiliarMonth <= 12); //si esta entre agosto y diciembre
                boolean even = auxiliarMonth%2==0; //Verifica si es par
                boolean haveTwentyEightDays = (auxiliarDay < 1 || auxiliarDay > 28); //se verifica si no esta entre los 28 dias de febrero
                boolean haveThirtyDays = (auxiliarDay < 1 || auxiliarDay > 30); //se verifica si no esta entre los 30 dias
                boolean haveThirtyOneDays = (auxiliarDay < 1 || auxiliarDay > 31);//se verifica si no esta entre los 31 dias
    
    
                /*
                 Los meses impares de enero a julio tienen 31 dias el resto 30 dias
                 Y los meses pares de agosto a diciembre tienen 31 dias y el resto 30 dias igual
                */
    
                if( (januaryToJuly && even && haveThirtyDays) || (januaryToJuly && !even && haveThirtyOneDays) || (february && haveTwentyEightDays) || (augustToDecember && even && haveThirtyOneDays) || (augustToDecember && !even && haveThirtyDays))
                    throw new IllegalArgumentException("Error de fecha");
    
                //Buscando error en el a�o
                Date actualDate = new Date();
                int year = 10*(id.charAt(0)-'0') + (id.charAt(1)-'0');//Obtengo los primeros digitos del ID
                int actually = actualDate.getYear() + 1900;
                year+= (actually - actually%100);
                if(year>actually)year-=100;
    
                int age = actually - year;
                if(age < MINIMUM_AGE || age > MAXIMUM_AGE)
                    throw new IllegalArgumentException("Error en la edad");   
                    int genre = id.charAt(9)-'0';
                
                //TODO Falta verificar el siglo con la edad septimo digito
                int centuryDigit = id.charAt(6)-'0';
                int centuryExpected = 0;
                int birthCentury = (year/100)+1;

                if(centuryDigit >= 0 && centuryDigit <= 5)
                    centuryExpected = 20;
                else if(centuryDigit >= 6 && centuryDigit <= 8)
                    centuryExpected = 21;
                else 
                    centuryExpected = 19;

                if(centuryExpected != birthCentury)
                {
                	System.out.println(centuryExpected + "\n"  + year);
                	throw new IllegalArgumentException("Error en el siglo");
                }
                    

                if(!(genre%2 == 1 && sex == Genre.FEMALE) && !(genre%2 == 0 && sex == Genre.MALE)) 
                    {
                	throw new IllegalArgumentException("Error sexo no compatible con el id");
                    }                                        
                
                if(sameID(id))
                    throw new IllegalArgumentException("ID repetido");
            }
            
        }
	}


    public static void checkStudentState(StatesStudent state)
    {
        if(state == null)
            throw new IllegalArgumentException("Estado vacio");
    }

    public static void checkWorkerState(GeneralState state)
    {
        if(state == null)
            throw new IllegalArgumentException("Estado vacio");            
    }

    public static void checkName(String name)
    {
        if(name == null)
            throw new IllegalArgumentException("Nombre vacio");
        if(name.equals("") || name.trim().equals(""))
            throw new IllegalArgumentException("Nombre vacio");
    }

    public static boolean checkComebackDate(Date comeBackDate, GeneralState state)throws Exception{
    	boolean check = false;
    	if(state instanceof StatesWorkerWithComebackDate){
			if(comeBackDate==null)
				throw new Exception("Se debe introducir la fecha de retorno del trabajador");
			check = true;
		}
		return check;
    }
}
