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
    /// Логика взаимодействия для UserFormWPF.xaml
    /// </summary>
    public partial class UserFormWPF : Window
    {
        public UserFormWPF()
        {
            InitializeComponent();
            Connection = ConfigurationManager.ConnectionStrings["DefaultConnection"].ConnectionString;
            NewNameField.Text = "";
            NewNameField.IsEnabled = false;
            NewSurnameField.Text = "";
            NewSurnameField.IsEnabled = false;
            NewPasswdField.Password = "";
            NewPasswdField.IsEnabled = false;
            NewPasswdField2.Password = "";
            NewPasswdField2.IsEnabled = false;
            UpdateDataBtn.IsEnabled = false;
            passwdField.Password = "";
        }
        SqlConnection sqlConn = null;
        string Connection = null;
        SqlCommand Com;
        SqlDataAdapter Data;
        DataTable dT;
        String login, passwd;
        int count = 0;
        private void AutorBtn_Click(object sender, RoutedEventArgs e)
        {
            login = loginField.Text;
            passwd = Encription(passwdField.Password, 3);
            sqlConn = new SqlConnection(Connection);
            sqlConn.Open();
            if (sqlConn.State == System.Data.ConnectionState.Open)
            {
                String strQ = "SELECT * FROM MainTable WHERE Login='" + login + "';"; Data = new SqlDataAdapter(strQ, sqlConn);
                dT = new DataTable("Користувачі системи");
                Data.Fill(dT);
                if (dT.Rows.Count == 0)
                    MessageBox.Show("Такого користувача на знайдено");
                else
                {
                    bool Status = Convert.ToBoolean(dT.Rows[0][4]);
                    if (!Status)
                        MessageBox.Show("Користувач заблокований Адміністратором системи!");
                    else
                    {
                        if ((dT.Rows[0][2].ToString() == login) && (dT.Rows[0][3].ToString() == passwd))
                        {
                            NewNameField.Text = dT.Rows[0][0].ToString(); 
                            NewSurnameField.Text = dT.Rows[0][1].ToString();
                            NewPasswdField.Password = "";
                            NewPasswdField2.Password = "";
                            NewNameField.IsEnabled = true;
                            NewSurnameField.IsEnabled = true;
                            NewPasswdField.IsEnabled = true;
                            NewPasswdField2.IsEnabled = true;
                            UpdateDataBtn.IsEnabled = true;
                        }
                        else
                        {
                            count++;
                            String s = "Невірно введений пароль! " + "Помилкове введення №" + count.ToString();
                            MessageBox.Show(s);
                            if (count == 3)
                                System.Windows.Application.Current.Shutdown();
                        }
                    }
                }
            }
            sqlConn.Close();
        }

        private void RegBtn_Click(object sender, RoutedEventArgs e)
        {
            sqlConn = new SqlConnection(Connection);
            sqlConn.Open();
            String nameReg = NameField.Text;
            String surnameReg = SurnameField.Text;
            String loginReg = loginRegField.Text;
            String passwdReg = passwdRegField.Password;
            String passwdReg2 = passwdRegField2.Password;
            String strQ;
            if (sqlConn.State == System.Data.ConnectionState.Open)
            {
                try
                {
                    if ((passwdReg == passwdReg2) && (loginReg != "") && (passwdReg != "") )
                    {
                        Boolean flag = RestrictionFunc(passwdReg);
                        if (flag == true)
                        {
                            strQ = "INSERT INTO MainTable ";
                            strQ += "VALUES ('" + nameReg + "', '" + surnameReg + "', '" + loginReg + "', '" + Encription(passwdReg, 3) + "', 'True', 'True'); ";
                            Com = new SqlCommand(strQ, sqlConn);
                            Com.ExecuteNonQuery();
                            NameField.Text = "";
                            SurnameField.Text = "";
                            loginRegField.Text = "";
                            passwdRegField.Password = "";
                            passwdRegField2.Password = "";
                        }
                        else
                        {
                            MessageBox.Show("Пароль не відповідає вимогам. Спробуйте ще раз!");
                        }
                    }
                    else
                    {
                        MessageBox.Show("Обліковий запис не створено. Спробуйте ще раз!");
                    }
                }
                catch
                {
                    MessageBox.Show("Користувач з таким логіном вже існує у системі!");
                }
            }
            sqlConn.Close();
        }

        private bool RestrictionFuncLogin(string loginReg)
        {
            
                if(RunSQL("SELECT Login FROM MainTable WHERE Login = '" + loginReg + "'") != null) ////
                    return false;
            
            return true;
        }
        public object RunSQL(string sql)
        {
            sqlConn = new SqlConnection(Connection);
            sqlConn.Open();
            SqlCommand command = new SqlCommand(sql, sqlConn);
            return command.ExecuteScalar();
        }
        private void UpdateDataBtn_Click(object sender, RoutedEventArgs e)
        {
            sqlConn = new SqlConnection(Connection);
            sqlConn.Open();
            String newname = NewNameField.Text;
            String newsurname = NewSurnameField.Text;
            String newpasswd = NewPasswdField.Password;
            String newpasswd2 = NewPasswdField2.Password;
            if (sqlConn.State == System.Data.ConnectionState.Open)
            {
                String strQ;
                if ((newpasswd == newpasswd2) && (newpasswd != ""))
                {
                    Boolean flag = RestrictionFunc(newpasswd);
                    if (Convert.ToBoolean(dT.Rows[0][5]) == true)
                    {
                        if (flag == true)
                        {
                            strQ = "UPDATE MainTable SET Name='" + newname + "', "; 
                            strQ += "Surname='" + newsurname + "', ";
                            strQ += "Password='" + Encription(newpasswd, 3) + "' ";
                            strQ += "WHERE Login='" + login + "';";
                            //MessageBox.Show(strQ); 
                            Com = new SqlCommand(strQ, sqlConn);
                            Com.ExecuteNonQuery();
                        }
                        else
                            MessageBox.Show("У паролі немає літер верхнього та нижнього регістрів, а також арифметичних операцій! Спробуйте знову!");
                    }
                    else
                    {
                        strQ = "UPDATE MainTable SET Name='" + newname + "', "; strQ += "Surname='" + newsurname + "', ";
                        strQ += "Password='" + Encription(newpasswd, 3) + "' ";
                        strQ += "WHERE Login='" + login + "';";
                        Com = new SqlCommand(strQ, sqlConn);
                        Com.ExecuteNonQuery();
                    }
                }
                else
                {
                    MessageBox.Show("Введено пустий пароль або новий пароль повторно введено некоректно!");
                }
            }
            sqlConn.Close();
        }
        private void CloseBtnSystem_Click(object sender, RoutedEventArgs e)
        {
            NewNameField.Text = ""; 
            NewNameField.IsEnabled = false;
            NewSurnameField.Text = ""; 
            NewSurnameField.IsEnabled = false;
            NewPasswdField.Password = ""; 
            NewPasswdField.IsEnabled = false; 
            NewPasswdField2.Password = ""; 
            NewPasswdField2.IsEnabled = false; 
            UpdateDataBtn.IsEnabled = false;
            passwdField.Password = "";
        }        
        Boolean RestrictionFunc(String Pass)
        {
            Byte Count1, Count2, Count3;
            Byte LenPass = (Byte)Pass.Length;
            Count1 = Count2 = Count3 = 0;
            for (Byte i = 0; i < LenPass; i++)
            {
                if ((Convert.ToInt32(Pass[i]) >= 65) && (Convert.ToInt32(Pass[i]) <= 65 + 25))
                    Count1++;
                if ((Convert.ToInt32(Pass[i]) >= 97) && (Convert.ToInt32(Pass[i]) <= 97 + 25))
                    Count2++;
                if ((Pass[i] == '+') || (Pass[i] == '-') || (Pass[i] == '*') || (Pass[i] == '/'))
                    Count3++;
            }
            return (Count1 * Count2 * Count3 != 0);
        }
        private void Exit_Click(object sender, RoutedEventArgs e)
        {
            MainWindow main = new MainWindow();
            Hide();
            main.Show();
        }
        
        public static char encription(char en, int key)
        {
            if (!char.IsLetter(en))
            {
                return en;
            }
            char d = char.IsUpper(en) ? 'A' : 'a'; 
            return (char)((((en + key) - d) % 26) + d);
        }
        public static string Encription(string InputText, int Key)
        {
            string Encripted = string.Empty;
            foreach (char en in InputText)
                Encripted += encription(en, Key);
            return Encripted;
        }

    }
}
