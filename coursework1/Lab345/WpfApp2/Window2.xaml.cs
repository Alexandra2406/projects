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

namespace WpfApp2
{
    /// <summary>
    /// Логика взаимодействия для Window2.xaml
    /// </summary>
    public partial class Window2 : Window
    {
        string connectionString = null;
        SqlConnection connection = null;
        SqlCommand command;
        SqlDataAdapter adapter;
        //Data Source=AlexK;Initial Catalog=kyrsdb;Integrated Security=True
        DataTable dT1;
        String strQ;

        public Window2()
        {
            InitializeComponent();
            connectionString = ConfigurationManager.ConnectionStrings["DefaultConnection"].ConnectionString;
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            connection = new SqlConnection(connectionString);
            connection.Open();
            if (connection.State == System.Data.ConnectionState.Open)
            {
                try
                {
                    string from = DelFrom.Text;
                    if (from == "SickID")
                    {
                        String Id = IDField.Text;
                        strQ = "";
                        strQ += "UPDATE SickID SET DataCure = '" + 0 + "' , DataSick = '" + 0 + "' ,  SickLeaves = '" + 0 + "' WHERE ID = '" + Id + "';";
                        command = new SqlCommand(strQ, connection);
                        MessageBox.Show(command.ExecuteNonQuery().ToString());
                        connection.Close();
                        strQ = null;
                        FullSalarY(Convert.ToInt32(Id));
                    }
                    else if (from == "SalaryID")
                    {
                        String Id = IDField.Text;
                        strQ = "";
                        strQ += "UPDATE PSFull SET Premium = '" + 0 + "' , Supplement = '" + 0 + "' , FullPS = '" + 0 + "' WHERE ID = '" + Id + "';";
                        command = new SqlCommand(strQ, connection);
                        MessageBox.Show(command.ExecuteNonQuery().ToString());
                        connection.Close();
                        strQ = null;
                        FullSalarY(Convert.ToInt32(Id));
                    }
                }
                catch { }
            }
        }
        public object RunSQL(string sql)
        {
            connection = new SqlConnection(connectionString);
            connection.Open();
            SqlCommand command = new SqlCommand(sql, connection);
            return command.ExecuteScalar();
        }
        private void Add_Click(object sender, RoutedEventArgs e)
        {
            connection = new SqlConnection(connectionString);
            connection.Open();
            if (connection.State == System.Data.ConnectionState.Open)
            {
                string from = DelFrom.Text;
                try
                {
                    if (from == "SickID")
                    {
                        String Id, DataS, DataC;
                        Id = IDField.Text;
                        DataS = DSick.Text;
                        DataC = Dcure.Text;
                        int Exp = Convert.ToInt32(RunSQL("SELECT Experience  from SalaryID where ID = " + Id));
                        double Per = Convert.ToDouble(RunSQL("SELECT PercentEx  from SalaryID where ID = " + Id));
                        int Aver = Convert.ToInt32(RunSQL("SELECT Average  from SalaryID where ID = " + Id));
                        int numofd = Convert.ToInt32(DataC) - Convert.ToInt32(DataS);
                        int sickl = Convert.ToInt32(1.0 * numofd * Aver * Per);
                        strQ = "";
                        strQ += "UPDATE SickID SET DataCure = " + DataC + " , DataSick = " + DataS + " ,  SickLeaves = " + sickl.ToString() + " WHERE ID = '" + Id + "';";
                        command = new SqlCommand(strQ, connection);
                        MessageBox.Show(command.ExecuteNonQuery().ToString());
                        connection.Close();
                        strQ = null;
                        FullSalarY(Convert.ToInt32(Id));
                        string sqlQ = "SELECT TOP (100) PERCENT dbo.ID.ID AS [№], " +
                 "dbo.ID.Name AS [Ім'я], " +
                 "dbo.ID.Surname AS Прізвище, " +
                 "dbo.ID.Patronymic AS [по батькові], " +
                 "dbo.ID.Position AS Позиція, " +
                 "dbo.SickID.DataSick AS [Дата захворювання], " +
                 "dbo.SickID.DataCure AS [Дата одужання], " +
                 "dbo.SickID.SickLeaves AS Лікарняні " +
                 "FROM     dbo.ID INNER JOIN " +
                 "dbo.SickID ON dbo.ID.ID = dbo.SickID.ID " +
                 "ORDER BY [№] ";
                        try
                        {
                            GetAndShowData(sqlQ, dt1);
                        }
                        catch (Exception ex)
                        {
                            MessageBox.Show(ex.Message);
                        }
                        IDField.Text = "Введіть ID";
                        DSick.Text = "Введіть дати захворювання";
                        Dcure.Text = "Введіть дату одужання";
                    }


                    else if (from == "SalaryID")
                    {
                        String Id, Pre, Sup;
                        Id = IDField.Text;
                        Pre = Prem.Text;
                        Sup = Nadb.Text;
                        int salary = Convert.ToInt32(RunSQL("SELECT Salary  from SalaryID where ID = " + Id));
                        int fpr = Convert.ToInt32((salary * Convert.ToInt32(Pre) / 100 + Convert.ToInt32(Sup)) - salary);
                        strQ = "";
                        strQ += "UPDATE PSFull SET Premium = " + Pre + " , Supplement = " + Sup + " , FullPS = " + fpr.ToString() + " WHERE ID = '" + Id + "';";
                        command = new SqlCommand(strQ, connection);
                        MessageBox.Show(command.ExecuteNonQuery().ToString());
                        connection.Close();
                        strQ = null;
                        FullSalarY(Convert.ToInt32(Id));
                        string sqlQ = "SELECT TOP (100) PERCENT dbo.ID.ID AS [№], " +
              "dbo.ID.Name AS [Ім'я], " +
              "dbo.ID.Surname AS Прізвище, " +
              "dbo.ID.Patronymic AS [по батькові], " +
              "dbo.ID.Position AS Позиція, " +
              "dbo.PSFull.Premium AS Премія, " +
              "dbo.PSFull.Supplement AS Надбавка, " +
              "dbo.PSFull.FullPS AS Всього " +
              "FROM     dbo.ID INNER JOIN " +
              "dbo.PSFull ON dbo.ID.ID = dbo.PSFull.ID " +
              "ORDER BY [№] ";
                        try
                        {
                            GetAndShowData(sqlQ, dt1);
                        }
                        catch (Exception ex)
                        {
                            MessageBox.Show(ex.Message);
                        }
                        IDField.Text = "Введіть ID";
                        Prem.Text = "Введіть премію у відсотках";
                        Nadb.Text = "Введіть надбавку";
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message);
                }
            }

        }
        public void FullSalarY(int Id)
        {
            connection = new SqlConnection(connectionString);
            connection.Open();
            if (connection.State == System.Data.ConnectionState.Open)
            {
                int fpr = Convert.ToInt32(RunSQL("SELECT FullPS  from PSFull where ID = " + Id));
                int salary = Convert.ToInt32(RunSQL("SELECT Salary  from SalaryID where ID = " + Id));
                int dataC = Convert.ToInt32(RunSQL("SELECT DataCure  from SickID where ID = " + Id));
                int dataS = Convert.ToInt32(RunSQL("SELECT DataSick  from SickID where ID = " + Id));
                int Aver = Convert.ToInt32(RunSQL("SELECT Average  from SalaryID where ID = " + Id));
                int sickl = Convert.ToInt32(RunSQL("SELECT SickLeaves from SickID where ID = " + Id));
                int S = salary - (dataC - dataS) * Aver + sickl + fpr;
                int tax, tax1;
                tax1 = S / 100 * 40;
                if (S > 20000)
                    tax = S / 100 * 17;
                else
                    tax = S / 100 * 15;
                S -= tax;
                strQ = "";
                strQ += "UPDATE TaxID SET TaxSalary = " + tax.ToString() + "WHERE ID = '" + Id + "'; ";
                command = new SqlCommand(strQ, connection);
                MessageBox.Show(command.ExecuteNonQuery().ToString());
                strQ = "";
                strQ += "UPDATE TaxID SET Tax = " + tax1.ToString() + "WHERE ID = '" + Id + "'; ";
                command = new SqlCommand(strQ, connection);
                MessageBox.Show(command.ExecuteNonQuery().ToString());
                strQ = "";
                strQ += "UPDATE SalFull SET FullSalary = " + S.ToString() + "WHERE ID = '" + Id + "'; ";
                command = new SqlCommand(strQ, connection);
                MessageBox.Show(command.ExecuteNonQuery().ToString());
                connection.Close();
            }
        }

        private void Return_Click(object sender, RoutedEventArgs e)
        {
            MainWindow mw = new MainWindow();
            Hide();
            mw.Show();
        }
        private void FullSalary_Click(object sender, RoutedEventArgs e)
        {
            string sqlQ = "SELECT TOP (100) PERCENT dbo.ID.ID AS [№], " +
                "dbo.ID.Name AS [Ім'я], " +
                "dbo.ID.Surname AS Прізвище, " +
                "dbo.ID.Patronymic AS [по батькові], " +
                "dbo.ID.Position AS Позиція, " +
                "dbo.SalaryID.Salary AS Оклад, " +
                "dbo.TaxID.TaxSalary AS [Податок пряцівника], " +
                "dbo.SickID.SickLeaves AS Лікарняні, " +
                "dbo.PSFull.FullPS AS Надбавки, " +
                "dbo.SalFull.FullSalary AS Всього " +
                "FROM     dbo.ID INNER JOIN " +
                "dbo.SalFull ON dbo.ID.ID = dbo.SalFull.ID INNER JOIN " +
                "dbo.SalaryID ON dbo.ID.ID = dbo.SalaryID.ID INNER JOIN " +
                "dbo.TaxID ON dbo.ID.ID = dbo.TaxID.ID INNER JOIN " +
                "dbo.SickID ON dbo.ID.ID = dbo.SickID.ID INNER JOIN " +
                "dbo.PSFull ON dbo.ID.ID = dbo.PSFull.ID " +
                "ORDER BY [№] ";
            try
            {
                GetAndShowData(sqlQ, dt1);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
        private void Salary_Click(object sender, RoutedEventArgs e)
        {
            string sqlQ = "SELECT TOP (100) PERCENT dbo.ID.ID AS [№], " +
               "dbo.ID.Name AS [Ім'я], " +
               "dbo.ID.Surname AS Прізвище, " +
               "dbo.ID.Patronymic AS [по батькові], " +
               "dbo.ID.Position AS Позиція, " +
               "dbo.SalaryID.Salary AS Оклад, " +
               "dbo.SalaryID.Average AS [У середньому за день], " +
               "dbo.SalaryID.Experience AS Стаж, " +
               "dbo.SalaryID.PercentEx AS Відсоток " +
               "FROM     dbo.ID INNER JOIN " +
               "dbo.SalaryID ON dbo.ID.ID = dbo.SalaryID.ID " +
               "ORDER BY [№] ";
            try
            {
                GetAndShowData(sqlQ, dt1);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
        private void Tax_Click(object sender, RoutedEventArgs e)
        {
            string sqlQ = "SELECT TOP (100) PERCENT dbo.ID.ID AS [№], " +
                           "dbo.ID.Name AS [Ім'я], " +
                           "dbo.ID.Surname AS Прізвище, " +
                           "dbo.ID.Patronymic AS [по батькові], " +
                           "dbo.ID.Position AS Позиція, " +
                           "dbo.TaxID.Tax AS [Податок підприємства], " +
                           "dbo.TaxID.TaxSalary AS [Податок пряцівника] " +
                           "FROM     dbo.ID INNER JOIN " +
                           "dbo.TaxID ON dbo.ID.ID = dbo.TaxID.ID " +
                           "ORDER BY [№] ";
            try
            {
                GetAndShowData(sqlQ, dt1);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
        private void MarSt_Click(object sender, RoutedEventArgs e)
        {
            string sqlQ = "SELECT TOP (100) PERCENT dbo.ID.ID AS [№], " +
               "dbo.ID.Name AS [Ім'я], " +
               "dbo.ID.Surname AS Прізвище, " +
               "dbo.ID.Patronymic AS [по батькові], " +
               "dbo.ID.Position AS Позиція, " +
               "dbo.MStatusID.MaritalStatus AS [Сімейний статус], " +
               "dbo.MStatusID.NumberOFChildren AS [Кількість дітей] " +
               "FROM     dbo.ID INNER JOIN " +
               "dbo.MStatusID ON dbo.ID.ID = dbo.MStatusID.ID " +
               "ORDER BY [№] ";
            try
            {
                GetAndShowData(sqlQ, dt1);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
        private void Sick_Click(object sender, RoutedEventArgs e)
        {
            string sqlQ = "SELECT TOP (100) PERCENT dbo.ID.ID AS [№], " +
                 "dbo.ID.Name AS [Ім'я], " +
                 "dbo.ID.Surname AS Прізвище, " +
                 "dbo.ID.Patronymic AS [по батькові], " +
                 "dbo.ID.Position AS Позиція, " +
                 "dbo.SickID.DataSick AS [Дата захворювання], " +
                 "dbo.SickID.DataCure AS [Дата одужання], " +
                 "dbo.SickID.SickLeaves AS Лікарняні " +
                 "FROM     dbo.ID INNER JOIN " +
                 "dbo.SickID ON dbo.ID.ID = dbo.SickID.ID " +
                 "ORDER BY [№] ";
            try
            {
                GetAndShowData(sqlQ, dt1);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
        private void Prem_Click(object sender, RoutedEventArgs e)
        {
            string sqlQ = "SELECT TOP (100) PERCENT dbo.ID.ID AS [№], " +
               "dbo.ID.Name AS [Ім'я], " +
               "dbo.ID.Surname AS Прізвище, " +
               "dbo.ID.Patronymic AS [по батькові], " +
               "dbo.ID.Position AS Позиція, " +
               "dbo.PSFull.Premium AS Премія, " +
               "dbo.PSFull.Supplement AS Надбавка, " +
               "dbo.PSFull.FullPS AS Всього " +
               "FROM     dbo.ID INNER JOIN " +
               "dbo.PSFull ON dbo.ID.ID = dbo.PSFull.ID " +
               "ORDER BY [№] ";
            try
            {
                GetAndShowData(sqlQ, dt1);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
        private void GetAndShowData(string SQLQuery, DataGrid dataGrid)
        {
            connection = new SqlConnection(connectionString);
            connection.Open();
            command = new SqlCommand(SQLQuery, connection);
            adapter = new SqlDataAdapter(command);
            DataTable Table = new DataTable();
            adapter.Fill(Table);
            dataGrid.ItemsSource = Table.DefaultView;
            connection.Close();
        }

    }
}