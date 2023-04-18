package com.nibss.eazibank.bank.services;

import com.nibss.eazibank.bank.models.Bank;
import com.nibss.eazibank.bank.repository.BankRepository;
import com.nibss.eazibank.staff.controller.requests.CreateStaffRequest;
import com.nibss.eazibank.staff.models.Staff;
import com.nibss.eazibank.staff.models.StaffDto;
import com.nibss.eazibank.staff.repository.StaffRepository;
import com.nibss.eazibank.staff.services.StaffServices;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class BankServicesMockTest {

    @Mock
    private BankRepository bankRepository;
    @Mock
    private StaffServices staffServices;
    @Mock
    private StaffRepository staffRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private Bank bank;
    @InjectMocks
    private BankServices bankServices  = new BankServicesImpl(bank);
    @Captor
    private ArgumentCaptor<Staff> argumentCaptor;

    @Test
    public void bankServicesExistTest() {
        BankServices bankServices = new BankServicesImpl(bank);
        assertNotNull(bankServices);
    }

    @Test
    public void bankCanAddStaffTest() {
        CreateStaffRequest createStaffRequest = new CreateStaffRequest("Arewa", "Ijaodola",
                "0908272748", "2001-01-01", "single");
        Staff createdStaff = Staff.builder()
                .staffId("1")
                .firstName("Arewa")
                .lastName("Ijaodola")
                .phoneNumber("0908272748")
                .build();
        StaffDto staffDtoReturned = StaffDto.builder()
                .staffId("1")
                .firstName("Arewa")
                .lastName("Ijaodola")
                .phoneNumber("0908272748")
                .build();

        when(staffRepository.save(any(Staff.class))).thenReturn(createdStaff);
        when(staffServices.createStaff(any(CreateStaffRequest.class))).thenReturn(staffDtoReturned);
        when(modelMapper.map(createdStaff, StaffDto.class)).thenReturn(staffDtoReturned);
        StaffDto newStaff = bankServices.createStaff(createStaffRequest);
        verify(staffRepository, times(1)).save(argumentCaptor.capture());

        Staff capturedStaff = argumentCaptor.getValue();
        assertThat(capturedStaff.getStaffId()).isEqualTo("1");

        assertThat(newStaff.getStaffId()).isEqualTo("1");
        assertThat(newStaff.getFirstName()).isEqualTo("Arewa");
        assertThat(newStaff.getLastName()).isEqualTo("Ijaodola");
        assertThat(newStaff.getPhoneNumber()).isEqualTo("0908272748");
    }

    @Test
    void test() {
        List<String> mockList = mock(List.class);
        mockList.add("Pankaj");
        mockList.size();
        verify(mockList).add("Pankaj");
    }
}
