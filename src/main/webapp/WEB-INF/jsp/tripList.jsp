<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.9/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="webjars/datetimepicker/2.3.4/jquery.datetimepicker.css">

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><fmt:message key="trips.title"/></h3>
            <div class="view-box">
                <form:form method="post" class="form-horizontal" role="form" id="filter">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="startDate">Фильтр от даты:</label>

                        <div class="col-sm-2">
                            <input name="startDate" id="startDate" class="date-picker">
                        </div>

                        <label class="control-label col-sm-2" for="endDate">по дате:</label>

                        <div class="col-sm-2">
                            <input name="endDate" id="endDate" class="date-picker">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="startTime">Фильтр по времени:</label>

                        <div class="col-sm-2">
                            <input name="startTime" id="startTime" class="time-picker">
                        </div>

                        <label class="control-label col-sm-2" for="endTime">по времени:</label>

                        <div class="col-sm-2">
                            <input name="endTime" id="endTime" class="time-picker">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-8">
                            <button type="submit" class="btn btn-primary pull-right">Фильтр</button>
                        </div>
                    </div>
                </form:form>
                <a class="btn btn-sm btn-info" id="add"><fmt:message key="trips.add"/></a>
                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th>Пользователь</th>
                        <th>Водитель</th>
                        <th>Дата</th>
                        <th>Маршрут</th>
                        <th>Количество пассажиров</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><fmt:message key="trips.edit"/></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" method="post" id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="dateTime" class="control-label col-xs-3">Дата</label>

                        <div class="col-xs-9">
                            <input class="form-control" id="dateTime" name="dateTime" placeholder="Date">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Маршрут</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="description"
                                   placeholder="Description">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="calories" class="control-label col-xs-3">Количество пассажиров</label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="calories" name="calories"
                                   placeholder="2">
                        </div>
                    </div>
                    <div class="form-group" >
                        <label for="driver" class="control-label col-xs-3">Select driver:</label>
                        <div class="col-xs-9">
                        <select class="form-control" id="driver" name="driver">
                            <option>DRIVER_1</option>
                            <option>DRIVER_2</option>
                            <option>DRIVER_3</option>
                        </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">Сохранить</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="webjars/datetimepicker/2.3.4/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.9/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.2.4/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript" src="resources/js/tripDatatables.js"></script>
</html>