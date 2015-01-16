<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<fmt:setLocale value="${ currentLanguage }" />
<fmt:setBundle basename="i18n.user" />
<%-- We re-use the variables "message" and "value" to avoid memory consumption --%>
<et:front>
    <h1>
        <fmt:message key="registration.title" />
    </h1>

    <form method="post" action="${ formRoute }" id="form-registration">
        <div>
            <label for="form-registration-usertype">
                <fmt:message key="registration.usertype.label" />
            </label>
            <div>
                <select name="usertype" id="form-registration-usertype" required>
                    <c:set var="value" value="" />
                    <c:if test="${ user != null && user.usertype == 'individual' }"><c:set var="value" value="selected" /></c:if>
                    <option value="i" ${ value }><fmt:message key="registration.usertype.individual" /></option>
                    
                    <c:set var="value" value="" />
                    <c:if test="${ user != null && user.usertype == 'organization' }"><c:set var="value" value="selected" /></c:if>
                    <option value="o" ${ value }><fmt:message key="registration.usertype.organization" /></option>
                </select>
            </div>
            <c:if test="${ errors != null && errors.get('usertype') != null }">
                <c:forEach var="error" items="${ errors.get('usertype') }">
                    <div><c:out value="${ error }" /></div>
                </c:forEach>
            </c:if>
        </div>

        <div>
            <label for="form-registration-email">
                <fmt:message key="registration.email.label" />
            </label>
            <div>
                <c:set var="value" value="" />
                <c:if test="${ user != null && user.email != null }"><c:set var="value" value="${ user.email }" /></c:if>
                <fmt:message key="registration.email.placeholder" var="message" />
                <input type="email" name="email" id="form-registration-email" placeholder="${ message }" value="${ value }" required />
            </div>
            <c:if test="${ errors != null && errors.get('email') != null }">
                <c:forEach var="error" items="${ errors.get('email') }">
                    <div><c:out value="${ error }" /></div>
                </c:forEach>
            </c:if>
        </div>

        <div>
            <label for="form-registration-password1">
                <fmt:message key="registration.password1.label" />
            </label>
            <div>
                <fmt:message key="registration.password1.placeholder" var="message" />
                <input type="password" name="password1" id="form-registration-password1" placeholder="${ message }" required />
            </div>
            <c:if test="${ errors != null && errors.get('password1') != null }">
                <c:forEach var="error" items="${ errors.get('password1') }">
                    <div><c:out value="${ error }" /></div>
                </c:forEach>
            </c:if>
        </div>

        <div>
            <label for="form-registration-password2">
                <fmt:message key="registration.password2.label" />
            </label>
            <div>
                <fmt:message key="registration.password2.placeholder" var="message" />
                <input type="password" name="password2" id="form-registration-password2" placeholder="${ message }" required />
            </div>
            <c:if test="${ errors != null && errors.get('password2') != null }">
                <c:forEach var="error" items="${ errors.get('password2') }">
                    <div><c:out value="${ error }" /></div>
                </c:forEach>
            </c:if>
        </div>

        <div>
            <label for="form-registration-addressstreet">
                <fmt:message key="registration.addressstreet.label" />
            </label>
            <div>
                <c:set var="value" value="" />
                <c:if test="${ user != null && user.addressStreet != null }"><c:set var="value" value="${ user.addressStreet }" /></c:if>
                <fmt:message key="registration.addressstreet.placeholder" var="message" />
                <input type="text" name="addressstreet" id="form-registration-addressstreet" placeholder="${ message }" value="${ value }" />
            </div>
            <c:if test="${ errors != null && errors.get('addressstreet') != null }">
                <c:forEach var="error" items="${ errors.get('addressstreet') }">
                    <div><c:out value="${ error }" /></div>
                </c:forEach>
            </c:if>
        </div>

        <div>
            <label for="form-registration-addresszipcode">
                <fmt:message key="registration.addresszipcode.label" />
            </label>
            <div>
                <c:set var="value" value="" />
                <c:if test="${ user != null && user.addressZipcode != null }"><c:set var="value" value="${ user.addressZipcode }" /></c:if>
                <fmt:message key="registration.addresszipcode.placeholder" var="message" />
                <input type="text" name="addresszipcode" id="form-registration-addresszipcode"
                    placeholder="${ message }" value="${ value }" />
            </div>
            <c:if test="${ errors != null && errors.get('addresszipcode') != null }">
                <c:forEach var="error" items="${ errors.get('addresszipcode') }">
                    <div><c:out value="${ error }" /></div>
                </c:forEach>
            </c:if>
        </div>

        <div>
            <label for="form-registration-addresscity">
                <fmt:message key="registration.addresscity.label" />
            </label>
            <div>
                <c:set var="value" value="" />
                <c:if test="${ user != null && user.addressCity != null }"><c:set var="value" value="${ user.addressCity }" /></c:if>
                <fmt:message key="registration.addresscity.placeholder" var="message" />
                <input type="text" name="addresscity" id="form-registration-addresscity" placeholder="${ message }" value="${ value }" />
            </div>
            <c:if test="${ errors != null && errors.get('addresscity') != null }">
                <c:forEach var="error" items="${ errors.get('addresscity') }">
                    <div><c:out value="${ error }" /></div>
                </c:forEach>
            </c:if>
        </div>

        <div>
            <label for="form-registration-country">
                <fmt:message key="registration.country.label" />
            </label>
            <div>
                <c:set var="value" value="" />
                <c:if test="${ user != null && user.country != null }"><c:set var="value" value="${ user.country }" /></c:if>
                <fmt:message key="registration.country.placeholder" var="message" />
                <input type="text" name="country" id="form-registration-country" placeholder="${ message }" value="${ value }" />
            </div>
            <c:if test="${ errors != null && errors.get('country') != null }">
                <c:forEach var="error" items="${ errors.get('country') }">
                    <div><c:out value="${ error }" /></div>
                </c:forEach>
            </c:if>
        </div>

        <div>
            <label for="form-registration-phonenumber">
                <fmt:message key="registration.phonenumber.label" />
            </label>
            <div>
                <c:set var="value" value="" />
                <c:if test="${ user != null && user.phoneNumber != null }"><c:set var="value" value="${ user.phoneNumber }" /></c:if>
                <fmt:message key="registration.phonenumber.placeholder" var="message" />
                <input type="tel" name="phonenumber" id="form-registration-phonenumber" placeholder="${ message }" />
            </div>
            <c:if test="${ errors != null && errors.get('phonenumber') != null }">
                <c:forEach var="error" items="${ errors.get('phonenumber') }">
                    <div><c:out value="${ error }" /></div>
                </c:forEach>
            </c:if>
        </div>

        <div id="form-registration-individual">
            <div>
                <label for="form-registration-firstname">
                    <fmt:message key="registration.firstname.label" />
                </label>
                <div>
                    <c:set var="value" value="" />
                    <c:if test="${ user != null && user.usertype == 'i' && user.firstname != null }"><c:set var="value" value="${ user.firstname }" /></c:if>
                    <fmt:message key="registration.firstname.placeholder" var="message" />
                    <input type="text" name="firstname" id="form-registration-firstname" placeholder="${ message }" />
                </div>
                <c:if test="${ errors != null && errors.get('firstname') != null }">
                    <c:forEach var="error" items="${ errors.get('firstname') }">
                        <div><c:out value="${ error }" /></div>
                    </c:forEach>
                </c:if>
            </div>

            <div>
                <label for="form-registration-lastname">
                    <fmt:message key="registration.lastname.label" />
                </label>
                <div>
                    <c:set var="value" value="" />
                    <c:if test="${ user != null && user.usertype == 'i' && user.lastname != null }"><c:set var="value" value="${ user.lastname }" /></c:if>
                    <fmt:message key="registration.lastname.placeholder" var="message" />
                    <input type="text" name="lastname" id="form-registration-lastname" placeholder="${ message }" />
                </div>
                <c:if test="${ errors != null && errors.get('lastname') != null }">
                    <c:forEach var="error" items="${ errors.get('lastname') }">
                        <div><c:out value="${ error }" /></div>
                    </c:forEach>
                </c:if>
            </div>

            <div>
                <label for="form-registration-birthday">
                    <fmt:message key="registration.birthday.label" />
                </label>
                <div>
                    <c:set var="value" value="" />
                    <c:if test="${ user != null && user.usertype == 'i' && user.birthday != null }"><c:set var="value" value="${ user.birthday }" /></c:if>
                    <fmt:message key="registration.birthday.placeholder" var="message" />
                    <input type="date" name="birthday" id="form-registration-birthday" placeholder="${ message }" value="${ value }" />
                </div>
                <c:if test="${ errors != null && errors.get('birthday') != null }">
                    <c:forEach var="error" items="${ errors.get('birthday') }">
                        <div><c:out value="${ error }" /></div>
                    </c:forEach>
                </c:if>
            </div>
        </div>

        <div id="form-registration-organization">

            <div>
                <label for="form-registration-orgname">
                    <fmt:message key="registration.orgname.label" />
                </label>
                <div>
                    <c:set var="value" value="" />
                    <c:if test="${ user != null && user.usertype == 'o' && user.name != null }"><c:set var="value" value="${ user.name }" /></c:if>
                    <fmt:message key="registration.orgname.placeholder" var="message" />
                    <input type="text" name="orgname" id="form-registration-orgname" placeholder="${ message }" value="${ value }" />
                </div>
                <c:if test="${ errors != null && errors.get('orgname') != null }">
                    <c:forEach var="error" items="${ errors.get('orgname') }">
                        <div><c:out value="${ error }" /></div>
                    </c:forEach>
                </c:if>
            </div>

            <div>
                <label for="form-registration-activity">
                    <fmt:message key="registration.activity.label" />
                </label>
                <div>
                    <c:set var="value" value="" />
                    <c:if test="${ user != null && user.usertype == 'o' && user.activity != null }"><c:set var="value" value="${ user.activity }" /></c:if>
                    <fmt:message key="registration.activity.placeholder" var="message" />
                    <input type="text" name="activity" id="form-registration-activity" placeholder="${ message }" value="${ value }" />
                </div>
                <c:if test="${ errors != null && errors.get('activity') != null }">
                    <c:forEach var="error" items="${ errors.get('activity') }">
                        <div><c:out value="${ error }" /></div>
                    </c:forEach>
                </c:if>
            </div>

            <div>
                <label for="form-registration-type">
                    <fmt:message key="registration.type.label" />
                </label>
                <div>
                    <c:set var="value" value="" />
                    <c:if test="${ user != null && user.usertype == 'o' && user.type != null }"><c:set var="value" value="${ user.type }" /></c:if>
                    <fmt:message key="registration.type.placeholder" var="message" />
                    <input type="text" name="type" id="form-registration-type" placeholder="${ message }" value="${ value }" />
                </div>
                <c:if test="${ errors != null && errors.get('type') != null }">
                    <c:forEach var="error" items="${ errors.get('type') }">
                        <div><c:out value="${ error }" /></div>
                    </c:forEach>
                </c:if>
            </div>
        </div>

        <div>
            <label for="form-registration-avatar">
                <fmt:message key="registration.avatar.label" />
            </label>
            <div>
                <c:set var="value" value="" />
                <c:if test="${ user != null && user.avatar != null }"><c:set var="value" value="${ user.avatar }" /></c:if>
                <fmt:message key="registration.avatar.placeholder" var="message" />
                <input type="url" name="avatar" id="form-registration-avatar" placeholder="${ message }" value="${ value }" />
            </div>
            <c:if test="${ errors != null && errors.get('avatar') != null }">
                <c:forEach var="error" items="${ errors.get('avatar') }">
                    <div><c:out value="${ error }" /></div>
                </c:forEach>
            </c:if>
        </div>

        <div>
            <fmt:message key="registration.submit.label" var="message" />
            <input type="submit" value="${ message }" />
        </div>
    </form>
    
    <script>
        function refreshDetail() {
            var value = document.querySelector('#form-registration-usertype').value;
        	
            if (value === 'i') {
            	document.querySelector('#form-registration-individual').classList.remove('hidden');
            	document.querySelector('#form-registration-organization').classList.add('hidden');
            } else {
                document.querySelector('#form-registration-organization').classList.remove('hidden');
                document.querySelector('#form-registration-individual').classList.add('hidden');
            }
        }

        document.querySelector('#form-registration-usertype').addEventListener('change', refreshDetail, false);
        refreshDetail();
    </script>
</et:front>