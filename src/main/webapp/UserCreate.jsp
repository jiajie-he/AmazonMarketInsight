<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Create a User</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6 mx-auto">
                <h1 class="text-center mb-4">Create User</h1>
                <form action="create" method="post" class="border p-4 shadow-lg rounded">
                <!-- <input type="hidden" name="action" value="create"> -->
                    <div class="form-group">
                        <label for="username">UserName</label>
                        <input type="text" id="username" name="username" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="firstname">FirstName</label>
                        <input type="text" id="firstname" name="firstname" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="lastname">LastName</label>
                        <input type="text" id="lastname" name="lastname" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password" class="form-control">
                    </div>
                    
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text" id="email" name="email" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="phonenumber">phoneNumber</label>
                        <input type="text" id="phonenumber" name="phonenumber" class="form-control">
                    </div>
                    
                    <!-- Repeat for other input fields -->
                    <div class="form-group">
                        <label for="subscribed">Subscribed (True or False)</label>
                        <input type="text" id="subscribed" name="subscribed" class="form-control">
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </form>
                <div class="text-center mt-4">
                    <span id="successMessage"><b>${messages.success}</b></span>
                </div>
            </div>
        </div>
    </div>
    <!-- Include Bootstrap JS and its dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
