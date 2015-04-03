package coop.ekologia.presentation.controller.user;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coop.ekologia.DTO.user.UserDTO;
import coop.ekologia.DTO.user.UserIndividualDTO;
import coop.ekologia.DTO.user.UserOrganizationDTO;
import coop.ekologia.presentation.EkologiaServlet;
import coop.ekologia.presentation.constants.UserConstants;
import coop.ekologia.presentation.controller.FormErrors;
import coop.ekologia.presentation.request.RoutingCentral;
import coop.ekologia.presentation.session.LoginSession;
import coop.ekologia.service.user.UserServiceInterface;
import coop.ekologia.service.utils.ConstraintsServiceInterface;
import coop.ekologia.service.utils.DateUtilitiesInterface;
import coop.ekologia.service.utils.StringUtilitiesInterface;

@WebServlet("/registration")
public class RegistrationServlet extends EkologiaServlet {
    private static final long serialVersionUID = -3319945240158127895L;
    
    @Inject
    private RoutingCentral router;
    
	@Inject
	private LoginSession loginSession;
    
    public static final String routing = "/registration";

    @EJB
    private UserServiceInterface userService;

    @Inject
    private FormErrors formErrors;

    @EJB
    private ConstraintsServiceInterface constraintsService;

    @EJB
    private StringUtilitiesInterface stringUtilities;

    @EJB
    private DateUtilitiesInterface dateUtilities;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardToJsp(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        formErrors.setLanguage(getCurrentLanguage(request));

        String userType = request.getParameter(UserConstants.PARAMETER_USERTYPE);
        String email = request.getParameter(UserConstants.PARAMETER_EMAIL);
        String password1 = request.getParameter(UserConstants.PARAMETER_PASSWORD1);
        String password2 = request.getParameter(UserConstants.PARAMETER_PASSWORD2);
        String addressStreet = request.getParameter(UserConstants.PARAMETER_ADDRESSSTREET);
        String addressZipcode = request.getParameter(UserConstants.PARAMETER_ADDRESSZIPCODE);
        String addressCity = request.getParameter(UserConstants.PARAMETER_ADDRESSCITY);
        String country = request.getParameter(UserConstants.PARAMETER_COUNTRY);
        String phoneNumber = request.getParameter(UserConstants.PARAMETER_PHONENUMBER);
        String firstName = request.getParameter(UserConstants.PARAMETER_FIRSTNAME);
        String lastName = request.getParameter(UserConstants.PARAMETER_LASTNAME);
        String birthday = request.getParameter(UserConstants.PARAMETER_BIRTHDAY);
        String orgname = request.getParameter(UserConstants.PARAMETER_ORGNAME);
        String activity = request.getParameter(UserConstants.PARAMETER_ACTIVITY);
        String type = request.getParameter(UserConstants.PARAMETER_TYPE);
        String avatar = request.getParameter(UserConstants.PARAMETER_AVATAR);
        UserDTO userDTO = null;
        
        if (stringUtilities.isEqualOne(userType, "i", "o")) {
            // We continue to check the form only when this field is valid, avoiding future cast and null errors.
            // This should never happened if html is valid (using right select or input[radio])
            
            // Firtly, we need to create a userDTO object, so we check specific field in the same time.
            if ("i".equals(userType)) {
                UserIndividualDTO useriDTO = new UserIndividualDTO();
                useriDTO.setFirstname(firstName);
                useriDTO.setLastname(lastName);
                
                if (constraintsService.isNotEmpty(birthday) && constraintsService.isNotDate(birthday)) {
                    formErrors.addError(UserConstants.PARAMETER_BIRTHDAY, "user.registration.birthday.invalid");
                } else {
                    useriDTO.setBirthday(dateUtilities.stringToDate(birthday, true));
                }
                
                userDTO = useriDTO;
            } else {
                UserOrganizationDTO useroDTO = new UserOrganizationDTO();
                useroDTO.setName(orgname);
                useroDTO.setActivity(activity);
                useroDTO.setType(type);
                
                userDTO = useroDTO;
            }
            
            userDTO.setUsertype(userType);
            userDTO.setEmail(email);
            userDTO.setAddressStreet(addressStreet);
            userDTO.setAddressZipcode(addressZipcode);
            userDTO.setAddressCity(addressCity);
            userDTO.setCountry(country);
            userDTO.setPhoneNumber(phoneNumber);
            userDTO.setAvatar(avatar);
            
            if (constraintsService.isEmpty(email)) {
                formErrors.addError(UserConstants.PARAMETER_EMAIL, "user.registration.email.empty");
            } else if (constraintsService.isNotEmail(email)) {
                formErrors.addError(UserConstants.PARAMETER_EMAIL, "user.registration.email.invalid");
            } else if (userService.existsByEmail(email)) {
                formErrors.addError(UserConstants.PARAMETER_EMAIL, "user.registration.email.used");
            }
            
            if (constraintsService.isEmpty(password1)) {
                formErrors.addError(UserConstants.PARAMETER_PASSWORD1, "user.registration.password1.empty");
            } else if (constraintsService.isNotSecuredPassword(password1)) {
                formErrors.addError(UserConstants.PARAMETER_PASSWORD1, "user.registration.password1.weak");
            }
            
            if (constraintsService.isEmpty(password2)) {
                formErrors.addError(UserConstants.PARAMETER_PASSWORD2, "user.registration.password2.empty");
            } else if (stringUtilities.notEquals(password1, password2)) {
                formErrors.addError(UserConstants.PARAMETER_PASSWORD2, "user.registration.password2.different");
            }
            
            if (constraintsService.isNotEmpty(avatar) && constraintsService.isNotUrl(avatar)) {
                formErrors.addError(UserConstants.PARAMETER_AVATAR, "user.registration.avatar.invalid");
            }
        } else {
            formErrors.addError(UserConstants.PARAMETER_USERTYPE, "user.registration.usertype.invalid");
        }

        if (formErrors.isEmpty()) {
            String salt = stringUtilities.generateCryptSalt();
            userDTO.setPassword(stringUtilities.crypt(password1, salt));
            userDTO.setSalt(salt);
            
            userService.insertUser(userDTO);
            
            loginSession.setUser(userDTO);
            
            response.sendRedirect(router.getHomepage());
        } else {
            request.setAttribute(UserConstants.ATTRIBUTE_ERRORS, formErrors.getErrors());
            if (userDTO != null) {
                request.setAttribute(UserConstants.ATTRIBUTE_USER, userDTO);
            }
            request.setAttribute("user", userDTO);
            forwardToJsp(request, response);
        }
    }
    
    private void forwardToJsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(UserConstants.ATTRIBUTE_FORMROUTE, router.getRegistration());
        forwardToJsp("/user/registration.jsp", request, response);
    }
}
