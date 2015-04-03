<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template"%>
<%@ page import="coop.ekologia.presentation.controller.cms.PageServlet"%>
<fmt:setLocale value="${ currentLanguage }" />
<fmt:setBundle basename="i18n.user" />
<%-- We re-use the variables "message" and "value" to avoid memory consumption --%>

<et:front>
	<jsp:attribute name="head">
	<script type="text/javascript"
			src="${pageContext.request.contextPath}/uikit-2.18.0/js/components/datepicker.min.js"></script>
	<script>
		function refreshDetail() {
			var value = document.querySelector('#form-registration-usertype').value;

			if (value === 'i') {
				document.querySelector('#form-registration-individual').classList
						.remove('hidden');
				document.querySelector('#form-registration-organization').classList
						.add('hidden');
			} else {
				document.querySelector('#form-registration-organization').classList
						.remove('hidden');
				document.querySelector('#form-registration-individual').classList
						.add('hidden');
			}
		}

		$(function() {
			document.querySelector('#form-registration-usertype')
					.addEventListener('change', refreshDetail, false);
			refreshDetail();

			//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx
			// Variable to store your files
			var files;

			// Add events
			$('input[type=file]').on('change', prepareUpload);

			// Grab the files and set them to our variable
			function prepareUpload(event) {
				files = event.target.files;
			}

			$('#imageUpload').on('click', uploadFiles);

			// Catch the form submit and upload the files
			function uploadFiles(event) {
				event.stopPropagation(); // Stop stuff happening
				event.preventDefault(); // Totally stop stuff happening

				// START A LOADING SPINNER HERE

				// Create a formdata object and add the files
				var data = new FormData();
				$.each(files, function(key, value) {
					data.append(key, value);
				});

				$.ajax({
					url : 'http://pix.toile-libre.org/?action=upload',
					method : 'POST',
					data : data,
					cache : false,
					dataType : 'jsonp',
					processData : false, // Don't process the files
					contentType : false
				// Set content type to false as jQuery will tell the server its a query string request

				}).done(function(data, textStatus, jqXHR) {
					if (typeof data.error === 'undefined') {
						// Success so call function to process the form
						submitForm(event, data);
					} else {
						// Handle errors here
						console.log('ERRORS: ' + data.error);
					}
				}).error(function(jqXHR, textStatus, errorThrown) {
					// Handle errors here
					console.log('ERRORS: ' + textStatus);
					// STOP LOADING SPINNER
				});
			}

		});
	</script>
	</jsp:attribute>
	<jsp:body>
	<h1>
		<fmt:message key="registration.title" />
	</h1>


	<form method="post" action="${ formRoute }" id="form-registration"
			class="uk-form">
		<div class="row panel">
			<div class="small-6 columns">
				<div class="row">
					<div class="small-4 columns">
						<label for="form-registration-usertype" class="right inline">
							<fmt:message key="registration.usertype.label" />
						</label>
					</div>
					<div class="small-8 columns">
						<select name="usertype" id="form-registration-usertype" required>
							<c:set var="value" value="" />
							<c:if test="${ user != null && user.usertype == 'i' }">
								<c:set var="value" value="selected" />
							</c:if>
							<option value="i" ${ value }><fmt:message
										key="registration.usertype.individual" /></option>

							<c:set var="value" value="" />
							<c:if test="${ user != null && user.usertype == 'o' }">
								<c:set var="value" value="selected" />
							</c:if>
							<option value="o" ${ value }><fmt:message
										key="registration.usertype.organization" /></option>
						</select>
						<c:if test="${ errors != null && errors.get('usertype') != null }">
							<small class="error"> <c:forEach var="error"
										items="${ errors.get('usertype') }">
									<c:out value="${ error }" />
									<br />
								</c:forEach>
							</small>
						</c:if>
					</div>
				</div>

				<div class="row">
					<div class="small-4 columns">
						<label for="form-registration-email" class="right inline">
							<fmt:message key="registration.email.label" />
						</label>
					</div>
					<div class="small-8 columns">
						<c:set var="value" value="" />
						<c:if test="${ user != null && user.email != null }">
							<c:set var="value" value="${ user.email }" />
						</c:if>
						<fmt:message key="registration.email.placeholder" var="message" />
						<input type="email" name="email" id="form-registration-email"
								placeholder="${ message }" value="${ value }" required />
						<c:if test="${ errors != null && errors.get('email') != null }">
							<small class="error"> <c:forEach var="error"
										items="${ errors.get('email') }">
									<c:out value="${ error }" />
									<br />
								</c:forEach>
							</small>
						</c:if>
					</div>
				</div>

				<div class="row">
					<div class="small-4 columns">
						<label for="form-registration-password1" class="right inline">
							<fmt:message key="registration.password1.label" />
						</label>
					</div>
					<div class="small-8 columns">
						<fmt:message key="registration.password1.placeholder"
								var="message" />
						<input type="password" name="password1"
								id="form-registration-password1" placeholder="${ message }"
								required />
						<c:if
								test="${ errors != null && errors.get('password1') != null }">
							<small class="error"> <c:forEach var="error"
										items="${ errors.get('password1') }">
									<c:out value="${ error }" />
									<br />
								</c:forEach>
							</small>
						</c:if>
					</div>
				</div>

				<div class="row">
					<div class="small-4 columns">
						<label for="form-registration-password2" class="right inline">
							<fmt:message key="registration.password2.label" />
						</label>
					</div>
					<div class="small-8 columns">
						<fmt:message key="registration.password2.placeholder"
								var="message" />
						<input type="password" name="password2"
								id="form-registration-password2" placeholder="${ message }"
								required />
						<c:if
								test="${ errors != null && errors.get('password2') != null }">
							<small class="error"> <c:forEach var="error"
										items="${ errors.get('password2') }">
									<c:out value="${ error }" />
									<br />
								</c:forEach>
							</small>
						</c:if>
					</div>
				</div>

				<div class="row">
					<div class="small-4 columns">
						<label for="form-registration-addressstreet" class="right inline">
							<fmt:message key="registration.addressstreet.label" />
						</label>
					</div>
					<div class="small-8 columns">
						<c:set var="value" value="" />
						<c:if test="${ user != null && user.addressStreet != null }">
							<c:set var="value" value="${ user.addressStreet }" />
						</c:if>
						<fmt:message key="registration.addressstreet.placeholder"
								var="message" />
						<input type="text" name="addressstreet"
								id="form-registration-addressstreet" placeholder="${ message }"
								value="${ value }" />
						<c:if
								test="${ errors != null && errors.get('addressstreet') != null }">
							<small class="error"> <c:forEach var="error"
										items="${ errors.get('addressstreet') }">
									<c:out value="${ error }" />
									<br />
								</c:forEach>
							</small>
						</c:if>
					</div>
				</div>

				<div class="row">
					<div class="small-4 columns">
						<label for="form-registration-addresszipcode" class="right inline">
							<fmt:message key="registration.addresszipcode.label" />
						</label>
					</div>
					<div class="small-8 columns">
						<c:set var="value" value="" />
						<c:if test="${ user != null && user.addressZipcode != null }">
							<c:set var="value" value="${ user.addressZipcode }" />
						</c:if>
						<fmt:message key="registration.addresszipcode.placeholder"
								var="message" />
						<input type="text" name="addresszipcode"
								id="form-registration-addresszipcode" placeholder="${ message }"
								value="${ value }" />
						<c:if
								test="${ errors != null && errors.get('addresszipcode') != null }">
							<small class="error"> <c:forEach var="error"
										items="${ errors.get('addresszipcode') }">
									<c:out value="${ error }" />
									<br />
								</c:forEach>
							</small>
						</c:if>
					</div>
				</div>

				<div class="row">
					<div class="small-4 columns">
						<label for="form-registration-addresscity" class="right inline">
							<fmt:message key="registration.addresscity.label" />
						</label>
					</div>
					<div class="small-8 columns">
						<c:set var="value" value="" />
						<c:if test="${ user != null && user.addressCity != null }">
							<c:set var="value" value="${ user.addressCity }" />
						</c:if>
						<fmt:message key="registration.addresscity.placeholder"
								var="message" />
						<input type="text" name="addresscity"
								id="form-registration-addresscity" placeholder="${ message }"
								value="${ value }" />
						<c:if
								test="${ errors != null && errors.get('addresscity') != null }">
							<small class="error"> <c:forEach var="error"
										items="${ errors.get('addresscity') }">
									<c:out value="${ error }" />
									<br />
								</c:forEach>
							</small>
						</c:if>
					</div>
				</div>

				<div class="row">
					<div class="small-4 columns">
						<label for="form-registration-country" class="right inline">
							<fmt:message key="registration.country.label" />
						</label>
					</div>
					<div class="small-8 columns">
						<c:set var="value" value="" />
						<c:if test="${ user != null && user.country != null }">
							<c:set var="value" value="${ user.country }" />
						</c:if>
						<fmt:message key="registration.country.placeholder" var="message" />
						<input type="text" name="country" id="form-registration-country"
								placeholder="${ message }" value="${ value }" />
						<c:if test="${ errors != null && errors.get('country') != null }">
							<small class="error"> <c:forEach var="error"
										items="${ errors.get('country') }">
									<c:out value="${ error }" />
									<br />
								</c:forEach>
							</small>
						</c:if>
					</div>
				</div>

				<div class="row">
					<div class="small-4 columns">
						<label for="form-registration-phonenumber" class="right inline">
							<fmt:message key="registration.phonenumber.label" />
						</label>
					</div>
					<div class="small-8 columns">
						<c:set var="value" value="" />
						<c:if test="${ user != null && user.phoneNumber != null }">
							<c:set var="value" value="${ user.phoneNumber }" />
						</c:if>
						<fmt:message key="registration.phonenumber.placeholder"
								var="message" />
						<input type="tel" name="phonenumber"
								id="form-registration-phonenumber" placeholder="${ message }"
								value="${value}" />
						<c:if
								test="${ errors != null && errors.get('phonenumber') != null }">
							<small class="error"> <c:forEach var="error"
										items="${ errors.get('phonenumber') }">
									<c:out value="${ error }" />
									<br />
								</c:forEach>
							</small>
						</c:if>
					</div>
				</div>
			</div>
			<div class="small-6 columns">
				<div id="form-registration-individual">
					<div class="row">
						<div class="small-4 columns">
							<label for="form-registration-firstname" class="right inline">
								<fmt:message key="registration.firstname.label" />
							</label>
						</div>
						<div class="small-8 columns">
							<c:set var="value" value="" />
							<c:if
									test="${ user != null && user.usertype == 'i' && user.firstname != null }">
								<c:set var="value" value="${ user.firstname }" />
							</c:if>
							<fmt:message key="registration.firstname.placeholder"
									var="message" />
							<input type="text" name="firstname"
									id="form-registration-firstname" placeholder="${ message }"
									value="${value}" />
							<c:if
									test="${ errors != null && errors.get('firstname') != null }">
								<small class="error"> <c:forEach var="error"
											items="${ errors.get('firstname') }">
										<c:out value="${ error }" />
										<br />
									</c:forEach>
								</small>
							</c:if>
						</div>
					</div>

					<div class="row">
						<div class="small-4 columns">
							<label for="form-registration-lastname" class="right inline">
								<fmt:message key="registration.lastname.label" />
							</label>
						</div>
						<div class="small-8 columns">
							<c:set var="value" value="" />
							<c:if
									test="${ user != null && user.usertype == 'i' && user.lastname != null }">
								<c:set var="value" value="${user.lastname}" />
							</c:if>
							<fmt:message key="registration.lastname.placeholder"
									var="message" />
							<input type="text" name="lastname"
									id="form-registration-lastname" placeholder="${ message }"
									value="${value}" />
							<c:if
									test="${ errors != null && errors.get('lastname') != null }">
								<small class="error"> <c:forEach var="error"
											items="${ errors.get('lastname') }">
										<c:out value="${ error }" />
										<br />
									</c:forEach>
								</small>
							</c:if>
						</div>
					</div>

					<div class="row">
						<div class="small-4 columns">
							<label for="form-registration-birthday" class="right inline">
								<fmt:message key="registration.birthday.label" />
							</label>
						</div>
						<div class="small-8 columns">
							<c:set var="value" value="" />
							<c:if
									test="${ user != null && user.usertype == 'i' && user.birthday != null }">
								<fmt:formatDate value="${user.birthday}" pattern="dd/MM/yyyy"
										var="value" />
							</c:if>
							<fmt:message key="registration.birthday.placeholder"
									var="message" />
							<input id="form-registration-birthday" name="birthday"
									type="text" value="${value}" placeholder="${ message }"
									data-uk-datepicker="{format:'DD/MM/YYYY',i18n:{ months:['Janvier','Fevrier','Mars','Avril','Mai','Juin','Juillet','Aout','Septembre','Octobre','Novembre','Decembre'], weekdays:['Dim','Lun','Mar','Mer','Jeu','Ven','Sam']}}" />
							<c:if
									test="${ errors != null && errors.get('birthday') != null }">
								<small class="erorr"> <c:forEach var="error"
											items="${ errors.get('birthday') }">
										<c:out value="${ error }" />
										<br />
									</c:forEach>
								</small>
							</c:if>
						</div>
					</div>
				</div>
				

				<div id="form-registration-organization">
					<div class="row">
						<div class="small-4 columns">
							<label for="form-registration-orgname" class="right inline">
								<fmt:message key="registration.orgname.label" />
							</label>
						</div>
						<div class="small-8 columns">
							<c:set var="value" value="" />
							<c:if
									test="${ user != null && user.usertype == 'o' && user.name != null }">
								<c:set var="value" value="${ user.name }" />
							</c:if>
							<fmt:message key="registration.orgname.placeholder" var="message" />
							<input type="text" name="orgname" id="form-registration-orgname"
									placeholder="${ message }" value="${ value }" />
							<c:if test="${ errors != null && errors.get('orgname') != null }">
								<small class="error"> <c:forEach var="error"
											items="${ errors.get('orgname') }">
										<c:out value="${ error }" />
										<br />
									</c:forEach>
								</small>
							</c:if>
						</div>
					</div>

					<div class="row">
						<div class="small-4 columns">
							<label for="form-registration-activity" class="right inline">
								<fmt:message key="registration.activity.label" />
							</label>
						</div>
						<div class="small-8 columns">
							<c:set var="value" value="" />
							<c:if
									test="${ user != null && user.usertype == 'o' && user.activity != null }">
								<c:set var="value" value="${ user.activity }" />
							</c:if>
							<fmt:message key="registration.activity.placeholder"
									var="message" />
							<input type="text" name="activity"
									id="form-registration-activity" placeholder="${ message }"
									value="${ value }" />
							<c:if
									test="${ errors != null && errors.get('activity') != null }">
								<small class="error"> <c:forEach var="error"
											items="${ errors.get('activity') }">
										<c:out value="${ error }" />
										<br />
									</c:forEach>
								</small>
							</c:if>
						</div>
					</div>

					<div class="row">
						<div class="small-4 columns">
							<label for="form-registration-type" class="right inline">
								<fmt:message key="registration.type.label" />
							</label>
						</div>
						<div class="small-8 columns">
							<c:set var="value" value="" />
							<c:if
									test="${ user != null && user.usertype == 'o' && user.type != null }">
								<c:set var="value" value="${ user.type }" />
							</c:if>
							<fmt:message key="registration.type.placeholder" var="message" />
							<input type="text" name="type" id="form-registration-type"
									placeholder="${ message }" value="${ value }" />
							<c:if test="${ errors != null && errors.get('type') != null }">
								<small class="error"> <c:forEach var="error"
											items="${ errors.get('type') }">
										<c:out value="${ error }" />
										<br />
									</c:forEach>
								</small>
							</c:if>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="small-4 columns">
						<label for="form-registration-avatar" class="right inline">
							<fmt:message key="registration.avatar.label" />
						</label>
					</div>
					<div class="small-8 columns">
						<c:set var="value" value="" />
						<c:if test="${ user != null && user.avatar != null }">
							<c:set var="value" value="${ user.avatar }" />
						</c:if>
						<fmt:message key="registration.avatar.placeholder" var="message" />
						<fmt:message key="registration.avatar.uploadAdvice"
								var="uploadAdvice" />
						<input type="url" name="avatar" id="form-registration-avatar"
								placeholder="${ message }" value="${ value }" data-uk-tooltip
								title="${uploadAdvice}" />
							<c:if test="${ errors != null && errors.get('avatar') != null }">
							<small class="error"> <c:forEach var="error"
										items="${ errors.get('avatar') }">
									<c:out value="${ error }" />
									<br />
								</c:forEach>
							</small>
						</c:if>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="right inline">
				<a href="${ routing.homepage }" class="button tiny"> <fmt:message
							key="registration.return.label" />
				</a>
				<button type="submit" class="button tiny">
					<fmt:message key="registration.submit.label" />
				</button>
			</div>
		</div>
	</form>

	</jsp:body>
</et:front>