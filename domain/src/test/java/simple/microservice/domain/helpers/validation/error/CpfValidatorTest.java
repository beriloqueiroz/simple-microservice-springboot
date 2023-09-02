package simple.microservice.domain.helpers.validation.error;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CpfValidatorTest {

	@Test
	public void testIsValid() {
		CpfValidator validator = new CpfValidator();
		assertTrue(validator.isValid("01385311371", null));
		assertTrue(validator.isValid("53487567687", null));
		assertTrue(validator.isValid("75920409991", null));
		assertTrue(validator.isValid("44902204991", null));
		assertTrue(validator.isValid("00613044762", null));

	}

	@Test
	public void testIsNotValid() {
		CpfValidator validator = new CpfValidator();
		assertFalse(validator.isValid("11111111111", null));
		assertFalse(validator.isValid("99999999999", null));
		assertFalse(validator.isValid("01385311372", null));
		assertFalse(validator.isValid("53487517687", null));
		assertFalse(validator.isValid("75920419991", null));
		assertFalse(validator.isValid("44902214991", null));
		assertFalse(validator.isValid("00613014762", null));
		assertFalse(validator.isValid("00000", null));

	}

}
