import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    @org.junit.jupiter.api.Test
    void testSetAnnualFee() {
        //act
        Member member = new Member("Peter", "Petersen", "petersen123@yahoo.dk", 20304050, "23/01/1960", "24/11/2023", true, true, "pepe0001");

        //arrange
        member.getAnnualFee(member);

        //assert
        assertEquals(1200, member.getAnnualFee());


    }



    }
