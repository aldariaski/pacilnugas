package com.pacilnugas.activities.service;

import com.pacilnugas.activities.model.Assignment;
import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.repository.AssignmentRepository;
import com.pacilnugas.activities.repository.MatkulRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class MatkulAssignServiceImplTest {
    @Mock
    private AssignmentRepository assignmentRepository;
    @Mock
    private MatkulRepository matkulRepository;
    @InjectMocks
    private AssignmentServiceImpl assignmentService;
    @InjectMocks
    private MatkulServiceImpl matkulService;

    private Matkul matkul1;
    private Matkul matkul2;
    private Assignment assignment1;
    private Assignment assignment2;
    private List<Assignment> semuaAss;

    @BeforeEach
    private void setUp() {
        semuaAss = new ArrayList<>();
        //Setup matkul first
        matkul1 = new Matkul("Adpro");
        matkul1.setPengajar("budi");
        matkul1.setTitle("Adpro");
        matkul1.setDescription("Belajar programming");
        LocalDate waktumatkul1 = LocalDate.of(2020, 1, 8);
        matkul1.setMulai(waktumatkul1);
        matkul1.setTahun(2020);
        matkul1.setSemester("Ganjil");
        matkul1.setMajor("Ilmu Komputer");
        matkul1.setAngkatan(2019);

        matkulService.createMatkul("Statprob", "Belajar programming",
                waktumatkul1, 2020, "Ganjil", "Ilmu Komputer", 2019);
        matkul2 = matkulRepository.findByTitle("Adpro");

        //Assignments
        LocalDate deadline1 = LocalDate.of(2020, 1, 8);
        LocalTime time1 = LocalTime.of(12, 5);
        assignmentService.createAssignment("Tugas 1 Adpro", "Belajar Pattern", "Adpro",
                "2019", deadline1, time1);
        assignment1 = assignmentRepository.findByTitle("Tugas 1 Adpro");
        semuaAss.add(assignment1);

        LocalDateTime deadlinetime1 = LocalDateTime.of(deadline1, time1);
        assignment2 = new Assignment("Tugas 2", matkul1, deadlinetime1);
        assignment2.setTitle("Tugas 2");
        assignment2.setDescription("Sangat banyak");
        assignment2.setMatkul("Adpro");
        assignment2.setMatkulObject(matkul1);
        assignment2.setDeadline(deadline1);
        assignment2.setTime(time1);
    }

    @Test
    void testServiceCreateAss() {
        lenient().when(assignmentService.createAssignment2(assignment2)).thenReturn(assignment2);
    }


    @Test
    void testMatkulLombokDataHashcode() {
        int hashcode1 = assignment2.hashCode();
        assertEquals(-383761111, hashcode1);
    }

    @Test
    void testMatkulLombokDataToString() {
        String tostring1 = assignment2.toString();
        assertEquals("Assignment(matkulObject=Matkul(idAssignment=0, title=Adpro, "
                        + "description=Belajar programming, tahun=2020, mulai=2020-01-08, "
                        + "semester=Ganjil, major=Ilmu Komputer, angkatan=2019, pengajar=budi, "
                        + "listAssignment=null), matkul=Adpro, deadline=2020-01-08, time=12:05)",
                tostring1);
    }

    @Test
    void testMatkul2GetPlentiesFromModel() {
        assertEquals("Ilmu Komputer", assignment2.getMajor());
        assertEquals(2019, assignment2.getAngkatan());
        assertEquals("08 1 2020", assignment2.getDeadlineFormatted()); //versi gitlab
    }

    @Test
    void testMatkul2EqualsObject() {
        assertTrue(matkul1 instanceof Matkul);
        assertEquals(matkul1, matkul1);
        assertTrue(matkul1.equals(matkul1));
        Matkul matkulnull = null;
        assertNull(matkulnull);
    }

    @Test
    void testServiceGetDaftarPesan() {
        List<List> listSemua = new ArrayList<>();
        List<List> listoflist = assignmentService.getAllAssignment();
        assertEquals(listoflist, listSemua);
    }
}
