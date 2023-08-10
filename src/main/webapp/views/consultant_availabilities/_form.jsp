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
            <h5>Add New Availability</h5>
        </div>
        <div class="card-toolbar">
            <div class="d-flex justify-content-end">
                <a href="/online-appointments/views/consultant_availabilities/index.jsp"><button type="button" class="btn btn-primary">Back</button></a>
            </div>
        </div>
    </div>
    <div class="card-body">
        <form action="" method="post">
            <div class="form-group row">
                <div class="col-lg-6">
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Consultant</option>
                        <option value="1">Malinda 0</option>
                        <option value="2">Malinda 1</option>
                        <option value="3">Malinda 2</option>
                    </select>
                </div>
                <div class="col-lg-6">
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Available Day</option>
                        <option value="1">Sun Day</option>
                        <option value="2">Mon Day</option>
                        <option value="3">Fri Day</option>
                    </select>
                </div>
            </div>
            <div class="form-group row mt-3">
                <div class="col-lg-6">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="start-time" placeholder="start-time">
                        <label for="floatingInput">Start Time</label>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="form-floating mb-3">
                        <input type="tel" class="form-control" id="end-time" placeholder="end-time">
                        <label for="floatingInput">End Time</label>
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