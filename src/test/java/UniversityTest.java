import org.example.Student;
import org.example.StudentValueGenerator;
import org.example.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class) //Чтобы аннотация Mockito работала без доп настроек, автоматически
public class UniversityTest {
    private final Student student = new Student("Евгений", true);

    @InjectMocks
    University university;

    //Заглушка для класса StudentValueGenerator
    @Mock
    private StudentValueGenerator studentValueGenerator;

    /*
    @BeforeEach
    public void setUp(){
        university = new University(studentValueGenerator);
    }*/

    @Test
    public void getAllStudents(){
        assertNotNull(studentValueGenerator);


        //Контроль поведения генератора

        Mockito.when(studentValueGenerator.generateAge()).thenReturn(50);

        //Не так важен
        //Mockito.doReturn(50).when(studentValueGenerator.generateAge());

        university.addStudent(student);
        List<Student> expected = university.getAllStudents();
        assertEquals(expected.get(0).getAge(), 50);
    }

    @Test
    public void getAllStudentsOver50Years(){

        //Контроль поведения генератора

        //anyInt(), когда в генератор передаются параметры
        Mockito.when(studentValueGenerator.generateAgeInRange(anyInt(), anyInt())).thenReturn(55);

        //Не так важен
        //Mockito.doReturn(50).when(studentValueGenerator.generateAge());

        university.addStudentInRange(student, 50,100);
        List<Student> expected = university.getAllStudents();
        assertEquals(expected.get(0).getAge(), 55);
    }


    //Проверка, был ли вызван метод *
    @Test
    public void getAllStudentsWithCountAgeGenerate(){
        assertNotNull(studentValueGenerator);


        //Контроль поведения генератора

        Mockito.when(studentValueGenerator.generateAge()).thenReturn(50);

        //Не так важен
        //Mockito.doReturn(50).when(studentValueGenerator.generateAge());

        university.addStudent(student);
        List<Student> expected = university.getAllStudents();
        assertEquals(expected.get(0).getAge(), 50);

        //*
        Mockito.verify(studentValueGenerator, times(2)).generateAge();
    }


    //Проверка порядка вызова методов
    @Test
    public void getAllStudentsInOrder(){

        //Контроль поведения генератора

        //anyInt(), когда в генератор передаются параметры
        Mockito.when(studentValueGenerator.generateAgeInRange(anyInt(), anyInt())).thenReturn(55);

        //Не так важен
        //Mockito.doReturn(50).when(studentValueGenerator.generateAge());

        university.addStudentInRange(student, 50,100);

        InOrder inOrder = Mockito.inOrder(studentValueGenerator);

        List<Student> expected = university.getAllStudents();

        inOrder.verify(studentValueGenerator, times(2)).generateAge();
        inOrder.verify(studentValueGenerator).generateAgeInRange(anyInt(),anyInt());
        assertEquals(expected.get(0).getAge(), 55);
    }


}