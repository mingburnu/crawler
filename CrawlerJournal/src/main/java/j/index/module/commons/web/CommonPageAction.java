package j.index.module.commons.web;

import j.index.core.security.secUser.entity.SecUser;
import j.index.core.web.GenericWebAction;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * CommonPageAction
 * @author David Hsu
 * @version 2014/3/15
 */
@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("serial")
public class CommonPageAction extends GenericWebAction<SecUser> {
	
}
