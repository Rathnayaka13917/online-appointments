<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@include file="/layout/head.jsp"%>
<body>
<%@include file="/layout/nav_bar.jsp"%>
<div class="card mx-5 my-5">
    <div class="card-header border-0 pt-6 my-1">
        <div class="card-title">
            <h5>Add New Users</h5>
        </div>
        <div class="card-toolbar">
            <div class="d-flex justify-content-end">
                <a href="/online-appointments/users"><button type="button" class="btn btn-primary">Back</button></a>
            </div>
        </div>
    </div>
    <div class="card-body">
        <form action="" method="post">
            <div class="form-group row">
                <div class="col-lg-6">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="name" placeholder="eg:jone">
                        <label for="floatingInput">Name</label>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control" id="email" placeholder="jone@example.com">
                        <label for="floatingInput">Email Address</label>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-lg-6">
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="password" placeholder="password">
                        <label for="floatingInput">Password</label>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="con_password" placeholder="password">
                        <label for="floatingInput">Confirm Password</label>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-lg-6">
                    <select class="form-select" aria-label="Default select example">
                        <option selected>User Type</option>
                        <option value="1">Admin</option>
                        <option value="2">Job seeker</option>
                        <option value="3">Consultant</option>
                    </select>
                </div>
                <div class="col-lg-6">
                    <label for="status" class="required">Status</label>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked>
                        <label class="form-check-label" for="flexRadioDefault2">
                            Active
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
                        <label class="form-check-label" for="flexRadioDefault1">
                          InActive
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-lg-6"></div>
                <div class="col-lg-6" style="text-align-last: right;">
                    <button type="reset" class="btn btn-secondary btn-lg mr-3">
                        <i class="fas fa-redo"></i> Reset
                    </button>
                    <button type="submit" class="btn btn-primary btn-lg mr-3">
                        <i class="fas fa-save"></i> Save
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>