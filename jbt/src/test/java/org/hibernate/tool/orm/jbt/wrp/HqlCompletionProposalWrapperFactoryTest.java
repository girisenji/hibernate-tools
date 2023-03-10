package org.hibernate.tool.orm.jbt.wrp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.hibernate.tool.ide.completion.HQLCompletionProposal;
import org.hibernate.tool.orm.jbt.wrp.HqlCompletionProposalWrapperFactory.HqlCompletionProposalWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HqlCompletionProposalWrapperFactoryTest {
	
	private HqlCompletionProposalWrapper hqlCompletionProposalWrapper = null;
	private HQLCompletionProposal hqlCompletionProposalTarget = null;
	
	@BeforeEach
	public void beforeEach() {
		hqlCompletionProposalTarget = 
				new HQLCompletionProposal(
						HQLCompletionProposal.PROPERTY, 
						Integer.MAX_VALUE);
		hqlCompletionProposalWrapper = HqlCompletionProposalWrapperFactory
				.createHqlCompletionProposalWrapper(hqlCompletionProposalTarget);
	}
	
	@Test
	public void testConstruction() {
		assertNotNull(hqlCompletionProposalTarget);
		assertNotNull(hqlCompletionProposalWrapper);
	}
	
	@Test
	public void testGetCompletion() {
		assertNotEquals("foo", hqlCompletionProposalWrapper.getCompletion());
		hqlCompletionProposalTarget.setCompletion("foo");
		assertEquals("foo", hqlCompletionProposalWrapper.getCompletion());
	}
	

}
