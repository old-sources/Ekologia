<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="et" tagdir="/WEB-INF/tags/template" %>
<fmt:setLocale value="${ currentLanguage }"/>
<fmt:setBundle basename="i18n.cms"/>

<et:front>
    <et:breadcrumbs>
        <li><a class="current">
            <fmt:message key="menu.title"/>
        </a></li>
    </et:breadcrumbs>

    <div class="content">
        <h2>
            <fmt:message key="menu.title"/>
        </h2>

        <div class="row">
            <div class="small-6 columns">
                <div class="row">
                <ul class="button-group even-5">
                    <li><a href="#" class="small button secondary" id="menu-up"><fmt:message key="menu.detail.up" /></a></li>
                    <li><a href="#" class="small button" id="menu-create-root"><fmt:message key="menu.detail.create.root" /></a></li>
                    <li><a href="#" class="small button info" id="menu-create-child"><fmt:message key="menu.detail.create.child" /></a></li>
                    <li><a href="#" class="small button alert" id="menu-delete"><fmt:message key="menu.detail.delete" /></a></li>
                    <li><a href="#" class="small button secondary" id="menu-down"><fmt:message key="menu.detail.down" /></a></li>
                </ul>
                </div>
                <div class="row">
                    <div class="small-6 columns">
                        <div id="menu-tree"></div>
                    </div>
                </div>
            </div>
            <div class="small-6 columns">
                <fieldset>
                    <legend><fmt:message key="menu.detail.title"/></legend>

                    <label for="detail-name"><fmt:message key="menu.detail.name"/></label>
                    <input type="text" name="name" id="detail-name"/>

                    <label for="detail-route"><fmt:message key="menu.detail.route"/></label>
                    <select name="route" id="detail-route">

                    </select>

                    <h4><fmt:message key="menu.detail.parameters"/></h4>

                    <div id="detail-parameters"></div>
                </fieldset>
            </div>
        </div>
        <div class="row">
            <div class="small-12 columns">
                <form method="post" action="${ routing.getMenuManagement(role) }">
                    <input type="hidden" name="json" id="form-json" />
                    <fmt:message key="menu.submit" var="submit"/>
                    <input type="submit" value="${ submit }" class="button" />
                </form>
            </div>
        </div>
    </div>

    <script>
        var menuScope = {
            templates: {
                route: '<option value="__NAME__">__NAME__</option>',
                parameter: '<label for="parameter__NUM__">__KEY__</label><input type="text" name="parameters[__KEY__]" id="parameter__NUM__" data-key="__KEY__" />'
            },
            currentMenuId: 0
        };

        // Definition of class MenuItem
        (function(scope){
            scope.MenuItem = function(id, text, route, parameters, children) {
                this.id = id;
                this.text = text;
                this.route = route;
                this.parameters = parameters;
                this.children = children;
                this.state = { opened: true };
                this.parent = null;
            };

            scope.MenuItem.prototype.forEachParameter = function(callback){
                for (var key in this.parameters) {
                    if (this.parameters.hasOwnProperty(key)) {
                        callback(key, this.parameters[key]);
                    }
                }
            };

            scope.MenuItem.prototype.addChild = function(child, pos) {
                if (pos == null || pos < 0) {
                    this.children.push(child);
                } else {
                    this.children.splice(pos, 0, child);
                }
                child.parent = this;
            };

            scope.MenuItem.prototype.removeChild = function(id) {
                for (var i = 0, c = this.children.length ; i < c ; i++) {
                    if (this.children[i].id == id) {
                        this.children[i].parent = null;
                        this.children.splice(i, 1);
                        return;
                    }
                }
            };

            scope.MenuItem.prototype.toJson = function () {
                var result = {
                    id: this.id,
                    text: this.text,
                    route: this.route,
                    parameters: this.parameters,
                    children: [],
                    state: this.state = {opened: true}
                };
                for (var i = 0, c = this.children.length ; i < c ; i++) {
                    result.children.push(this.children[i].toJson());
                }
                return result;
            };

            scope.getMenuItem = function(id) {
                function findInNode(node) {
                    if (node.id == id) {
                        return node;
                    } else {
                        for (var i = 0, c = node.children.length ; i < c ; i++) {
                            var result = findInNode(node.children[i]);
                            if (result != null) {
                                return result;
                            }
                        }
                        return null;
                    }
                }
                var model = scope.model.data;
                for (var i = 0, c = model.length ; i < c ; i++) {
                    var result = findInNode(model[i]);
                    if (result != null) {
                        return result;
                    }
                }
                return null;
            };
        })(menuScope);

        // Definition of class ConfigurationItem
        (function(scope){
            scope.ConfigurationItem = function(code, parameters){
                this.code = code;
                this.parameters = parameters;
            };
        })(menuScope);

        // Definition of singleton model
        (function(scope){
            scope.model = {
                data: [],
                current: null,
                currentRootPosition: function(root){
                    if (this.current == null) {
                        return null;
                    }
                    var id;
                    var list;
                    if (root !== false) {
                        var current = this.current;
                        while (current.parent != null) {
                            current = current.parent;
                        }
                        id = current.id;
                        list = this.data;
                    } else {
                        id = this.current.id;
                        list = this.current.parent != null ? this.current.parent.children : this.data;
                    }
                    for (var i = 0, c = list.length; i < c; i++) {
                        if (list[i].id == id) {
                            return i;
                        }
                    }
                    return -1;
                },
                removeCurrent: function() {
                    if (this.current.parent != null) {
                        this.current.parent.removeChild(this.current.id);
                    } else {
                        for (var i = 0, c = this.data.length ; i < c ; i++) {
                            if (this.data[i].id == this.current.id) {
                                this.data.splice(i, 1);
                                return;
                            }
                        }
                    }
                },
                dataJson: function(){
                    var result = [];
                    for (var i = 0, c = this.data.length ; i < c ; i++) {
                        result.push(this.data[i].toJson());
                    }
                    return result;
                },
                save: function(){
                    function saveOne(elt) {
                        var result = {
                            name: elt.text,
                            route: elt.route,
                            parameters: elt.parameters,
                            children: []
                        };
                        for (var i = 0, c = elt.children.length ; i < c ; i++) {
                            result.children.push(saveOne(elt.children[i]));
                        }
                        return result;
                    }
                    var result = [];
                    for (var i = 0, c = this.data.length ; i < c ; i++) {
                        result.push(saveOne(this.data[i]));
                    }

                    $('#form-json').val(JSON.stringify(result));
                }
            };
        })(menuScope);

        // Definition of singleton configuration
        (function(scope){
            scope.configuration = {
                data: [],
                findByCode: function(code){
                    for (var i = 0, c = this.data.length ; i < c ; i++) {
                        if (this.data[i].code == code) {
                            return this.data[i];
                        }
                    }
                }
            };
        })(menuScope);

        // Save model in scope
        (function(scope){
            function makeMenuItem(menu) {
                scope.currentMenuId++;
                var result = new scope.MenuItem(scope.currentMenuId, menu.name, menu.route, menu.parameters, []);
                if (menu.children) {
                    for (var i = 0, c = menu.children.length; i < c; i++) {
                        result.addChild(makeMenuItem(menu.children[i]));
                    }
                }
                return result;
            }

            var data = JSON.parse('<c:out value="${menu.jsonEncoded}" escapeXml="false" />');
            for (var i = 0, c = data.length; i < c; i++) {
                scope.model.data.push(makeMenuItem(data[i]));
            }
        })(menuScope);

        // Save configuration in scope
        (function (scope) {
            <c:forEach var="conf" items="${ configuration }">
            (function () {
                var config = JSON.parse('${conf.toJson()}');
                scope.configuration.data.push(new scope.ConfigurationItem(config.code, config.parameters));
            })();
            </c:forEach>
        })(menuScope);

        // Construct route field
        (function(scope){
            var configs = scope.configuration.data;
            var $route = $('#detail-route');
            for (var i = 0, c = configs.length ; i < c ; i++) {
                var config = configs[i];
                $route.append($(scope.templates.route.replace(/__NAME__/g, config.code)));
            }
        })(menuScope);

        // onChange event listener definition
        (function(scope){
            scope.onTreeChange = function(menuItem){
                scope.model.current = menuItem;
                $('#detail-name').val(menuItem.text);
                $('#detail-route').val(menuItem.route).change();
                var $parameters = $('#detail-parameters').find('input');
                menuItem.forEachParameter(function(key, value){
                    $parameters.each(function(){
                        if ($(this).data('key') == key) {
                            $(this).val(value);
                        }
                    });
                });
            }
        })(menuScope);

        // bind right form with model
        (function(scope){
            var $name = $('#detail-name');
            var $route = $('#detail-route');
            var $parameters = $('#detail-parameters');
            $name.on('change', function(){
                scope.model.current.text = $name.val();
                scope.model.save();
            });
            $route.on('change', function(){
                scope.model.current.route = $route.val();
                scope.model.save();
            });
            $parameters.on('change', 'input', function(){
                var $this = $(this);
                scope.model.current.parameters[$this.data('key')] = $this.val();
                scope.model.save();
            });
        })(menuScope);

        // update parameters when route change
        (function(scope){
            $('#detail-route').on('change', function(){
                var $parameters = $('#detail-parameters');
                $parameters.empty();
                var route = $(this).val();
                if (route != null) {
                    var routeConfiguration = scope.configuration.findByCode(route);
                    for (var i = 0, c = routeConfiguration.parameters.length; i < c; i++) {
                        var param = routeConfiguration.parameters[i];
                        $parameters.append($(scope.templates.parameter.replace(/__KEY__/g, param).replace(/__NUM__/g, i)));
                    }
                }
            });
        })(menuScope);

        // Create and remove node
        (function(scope){
            $('#menu-create-root').on('click', function(e){
                e.preventDefault();
                scope.currentMenuId++;
                var menuItem = new scope.MenuItem(scope.currentMenuId, '', null, {}, []);
                var pos = scope.model.currentRootPosition() + 1;
                scope.model.data.splice(pos, 0, menuItem);
                scope.reloadTree();
            });

            $('#menu-create-child').on('click', function(e){
                e.preventDefault();
                scope.currentMenuId++;
                var menuItem = new scope.MenuItem(scope.currentMenuId, '', null, {}, []);
                var parent = scope.model.current;
                if (parent != null) {
                    parent.addChild(menuItem);
                    scope.reloadTree();
                }
            });

            $('#menu-delete').on('click', function(e){
                e.preventDefault();
                var menuItem = scope.model.current;
                if (menuItem != null) {
                    scope.model.removeCurrent();
                    scope.reloadTree();
                }
            });

            $('#menu-up').on('click', function(e){
                e.preventDefault();
                var model = scope.model;
                var pos = model.currentRootPosition(false);
                var list = model.current.parent != null ? model.current.parent.children : model.data;
                if (pos > 0) {
                    var tmp = list[pos];
                    list[pos] = list[pos-1];
                    list[pos-1] = tmp;
                    scope.reloadTree();
                }
            });

            $('#menu-down').on('click', function(e){
                e.preventDefault();
                var model = scope.model;
                var pos = model.currentRootPosition(false);
                var list = model.current.parent != null ? model.current.parent.children : model.data;
                if (pos >= 0 && pos < list.length-1) {
                    var tmp = list[pos];
                    list[pos] = list[pos+1];
                    list[pos+1] = tmp;
                    scope.reloadTree();
                }
            });
        })(menuScope);

        // Configure JsTree
        (function(scope){
            scope.reloadTree = function(){
                scope.model.save();
                var $menuTree = $('#menu-tree');
                $menuTree.jstree('destroy');
                $menuTree.on('changed.jstree', function(e, data){
                    if (data.node) {
                        scope.onTreeChange(scope.getMenuItem(data.node.id));
                    }
                });

                $menuTree.jstree({
                    core: {
                        data: scope.model.dataJson()
                    }
                });
            };

            scope.reloadTree();
        })(menuScope);
    </script>
</et:front>