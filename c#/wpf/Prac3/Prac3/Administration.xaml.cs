using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace Prac3
{
    /// <summary>
    /// Логика взаимодействия для Administration.xaml
    /// </summary>
    public partial class Administration : Window
    {
        public Administration()
        {
            InitializeComponent();
            Connection = ConfigurationManager.ConnectionStrings["DefaultConnection"].ConnectionString;
            RealAdminPasswd.Password = ""; RealAdminPasswd.IsEnabled = false;
            NewAdminPasswd.Password = ""; NewAdminPasswd.IsEnabled = false;
            NewAdminPasswd2.Password = ""; NewAdminPasswd2.IsEnabled = false;
            Prev.IsEnabled = false; Next.IsEnabled = false;
            UpdatePasswd.IsEnabled = false;
            AddUser.IsEnabled = false;
            CorrectStatusBtn.IsEnabled = false;
            CorrectRestrictionBtn.IsEnabled = false;
            AdminPasswd.Password = "";
            UsersLogins.Text = "";
        }
        SqlConnection sqlConn = null;
        string Connection= null;
        SqlCommand Com;
        SqlDataAdapter Data;
        DataTable dT;
        int LenTable;
        int index = 0;
        private void UpdatePasswd_Click(object sender, RoutedEventArgs e)
        {
            sqlConn = new SqlConnection(Connection);
            sqlConn.Open();
            String strQ;
            String RealPass = RealAdminPasswd.Password.ToString();
            String NewPass = NewAdminPasswd.Password.ToString();
            String NewPass2 = NewAdminPasswd2.Password.ToString();
            if ((RealPass == AdminPasswd.Password.ToString()) && (NewPass != "")
            && (NewPass == NewPass2))

            {
                if (sqlConn.State == System.Data.ConnectionState.Open)
                {
                    strQ = "UPDATE MainTable SET Password ='" + NewPass + "' WHERE Login = 'ADMIN'; ";
                    Com = new SqlCommand(strQ, sqlConn);
                    Com.ExecuteNonQuery();
                }
            }
            sqlConn.Close();
        }
        void UpdateDataTable()
        {
            sqlConn = new SqlConnection(Connection);
            sqlConn.Open();
            if (sqlConn.State == System.Data.ConnectionState.Open)
            {
                Data = new SqlDataAdapter("SELECT Name, Surname, Login, Status, Restriction FROM MainTable", sqlConn);
                dT = new DataTable("Користувачі системи");
                Data.Fill(dT);
                dataGrid.ItemsSource = dT.DefaultView;
                LenTable = dT.Rows.Count;
                Check();
            }
            sqlConn.Close();
        }
        private void Prev_Click(object sender, RoutedEventArgs e)
        {
            if (index > 0)
            {
                index--;
                UserNameSelected.Content = dT.Rows[index][0].ToString();
                UserSurnameSelected.Content = dT.Rows[index][1].ToString();
                UserLoginSelected.Content = dT.Rows[index][2].ToString();
                UserStatusSelected.Content = dT.Rows[index][3].ToString();
            }
        }
        private void Next_Click(object sender, RoutedEventArgs e)
        {
            if (index < LenTable - 1)
            {
                index++;
                UserNameSelected.Content = dT.Rows[index][0].ToString();
                UserSurnameSelected.Content = dT.Rows[index][1].ToString();
                UserLoginSelected.Content = dT.Rows[index][2].ToString();
                UserStatusSelected.Content = dT.Rows[index][3].ToString();
            }
        }
        private void AddUser_Click(object sender, RoutedEventArgs e)
        {
            sqlConn = new SqlConnection(Connection);
            sqlConn.Open();
            String strQ;
            String UserLogin = AddingUserLogin.Text;
            try
            {
                if (sqlConn.State == System.Data.ConnectionState.Open)
                {
                    strQ = "INSERT INTO MainTable (Name, Surname, Login, Status, Restriction) values('', '', '" + UserLogin + "', 1, 0); ";
                
                    Com = new SqlCommand(strQ, sqlConn);
                    Com.ExecuteNonQuery();
                }
                UpdateDataTable();
            }
            catch
            {
                MessageBox.Show("Користувача не додано! Можливо такий в базі вже є!");
            }
            sqlConn.Close();
        }
       void Check()
        {
            for (int i = 0; i < dT.Rows.Count; i++)
            {
                UsersLogins.Items.Add(dT.Rows[i][2].ToString());
            }
        }
        private void CorrectStatusBtn_Click(object sender, RoutedEventArgs e)
        {
            sqlConn = new SqlConnection(Connection);
            sqlConn.Open();
            String strQ;
            bool UserStatus = (bool)ChangeActive.IsChecked;
            if (sqlConn.State == System.Data.ConnectionState.Open)
            {
                strQ = "UPDATE MainTable SET Status ='" + UserStatus + "' WHERE Login='" +
                UsersLogins.SelectedValue.ToString() + "';";
                Com = new SqlCommand(strQ, sqlConn);
                Com.ExecuteNonQuery();
            }
            sqlConn.Close();
            UpdateDataTable();
        }
        private void CorrectRestrictionBtn_Click(object sender, RoutedEventArgs e)
        {
            sqlConn = new SqlConnection(Connection);
            sqlConn.Open();
            String strQ;
            bool UserRestriction = (bool)ChangeRestriction.IsChecked; if (sqlConn.State == System.Data.ConnectionState.Open)
            {
                strQ = "UPDATE MainTable SET Restriction ='" + UserRestriction + "' WHERE Login='" + UsersLogins.SelectedValue.ToString() + "';";
                Com = new SqlCommand(strQ, sqlConn);
                Com.ExecuteNonQuery();
            }
            sqlConn.Close();
            UpdateDataTable();
        }

        private void ExitFromSystem_Click(object sender, RoutedEventArgs e)
        {
            RealAdminPasswd.Password = ""; RealAdminPasswd.IsEnabled = false;
            NewAdminPasswd.Password = ""; NewAdminPasswd.IsEnabled = false;
            NewAdminPasswd2.Password = ""; NewAdminPasswd2.IsEnabled = false;
            Prev.IsEnabled = false; Next.IsEnabled = false;
            UpdatePasswd.IsEnabled = false;
            AddUser.IsEnabled = false;
            CorrectStatusBtn.IsEnabled = false;
            CorrectRestrictionBtn.IsEnabled = false;
            dT.Clear();
            dataGrid.ItemsSource = dT.DefaultView;
            AdminPasswd.Password = "";
            UsersLogins.Text = "";
        }
        public object RunSQL(string sql)
        {
            sqlConn = new SqlConnection(Connection);
            sqlConn.Open();
            SqlCommand command = new SqlCommand(sql, sqlConn);
            return command.ExecuteScalar();
        }
        int count = 0;
        private void Enter_Click(object sender, RoutedEventArgs e)
        {
            string passwd = AdminPasswd.Password;
            string pas = RunSQL("SELECT Password FROM MainTable WHERE Login = 'ADMIN'").ToString();
            if (pas == passwd)
            {
                RealAdminPasswd.Password = ""; RealAdminPasswd.IsEnabled = true;
                NewAdminPasswd.Password = ""; NewAdminPasswd.IsEnabled = true;
                NewAdminPasswd2.Password = ""; NewAdminPasswd2.IsEnabled = true;
                Prev.IsEnabled = true; Next.IsEnabled = true;
                UpdatePasswd.IsEnabled = true;
                AddUser.IsEnabled = true;
                CorrectStatusBtn.IsEnabled = true;
                CorrectRestrictionBtn.IsEnabled = true;
                UpdateDataTable();
            }
            else
            {
                AdminPasswd.Password = "";
                count++;
                String s = "Невірно введений пароль! " +
                "Помилкове введення №" + count.ToString();
                MessageBox.Show(s);
                if (count == 3)
                    System.Windows.Application.Current.Shutdown();
            }
        }
        private void Exit_Click(object sender, RoutedEventArgs e)
        {
            MainWindow main = new MainWindow();
            Hide();
            main.Show();
        }
    }
}

