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
    /// Логика взаимодействия для Window1.xaml
    /// </summary>
    public partial class Window1 : Window
    {
        string connectionString = null;
        SqlConnection connection = null;
        SqlCommand command;
        SqlDataAdapter adapter;
        //Data Source=AlexK;Initial Catalog=kyrsdb;Integrated Security=True
        public Window1()
        {
            InitializeComponent();
            connectionString = ConfigurationManager.ConnectionStrings["DefaultConnection"].ConnectionString;
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
        public object RunSQL(string sql)
        {
            connection = new SqlConnection(connectionString);
            connection.Open();
            SqlCommand command = new SqlCommand(sql, connection);
            return command.ExecuteScalar();
        }
        private void Button_Click(object sender, RoutedEventArgs e)
        {
            SumTax.Content = null;
            SumTaxSal.Content = null;
            string sqlQ = "SELECT TOP (100) PERCENT dbo.ID.ID AS [№], " +
                "dbo.ID.Name AS [Ім'я], " +
                "dbo.ID.Surname AS Прізвище, " +
                "dbo.ID.Patronymic AS [по батькові], " +
                "dbo.ID.Position AS Позиція, " +
                "dbo.SalFull.FullSalary AS Зарплата " +
                "FROM     dbo.ID INNER JOIN " +
                "dbo.SalFull ON dbo.ID.ID = dbo.SalFull.ID " +
                "ORDER BY [№] ";

            try
            {
                GetAndShowData(sqlQ, DataGrid1);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            SumTax.Content = null;
            SumTaxSal.Content = null;
            string sqlQ = "SELECT TOP (100) PERCENT dbo.ID.ID AS [№], " +
            "dbo.ID.Name AS [Ім'я], " +
            "dbo.ID.Surname AS Прізвище, " +
            "dbo.ID.Patronymic AS [по батькові], " +
            "dbo.ID.Position AS Позиція, " +
            "dbo.SickID.DataSick AS [Дати захворювання], " +
            "dbo.SickID.DataCure AS [Дати одужання] " +
            "FROM     dbo.ID INNER JOIN " +
            "dbo.SickID ON dbo.ID.ID = dbo.SickID.ID " +
            "ORDER BY [№] ";
            try
            {
                GetAndShowData(sqlQ, DataGrid1);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void Button_Click_2(object sender, RoutedEventArgs e)
        {
            string sqlQ = "SELECT TOP (100) PERCENT dbo.ID.ID AS [№]," +
          "dbo.ID.Name AS [Ім'я]," +
          "dbo.ID.Surname AS Прізвище," +
          "dbo.ID.Patronymic AS [по батькові]," +
          "dbo.ID.Position AS Позиція," +
          "dbo.TaxID.Tax AS [Податок підприємства], " +
          "dbo.TaxID.TaxSalary AS [Податок працівника] " +
          "FROM     dbo.ID INNER JOIN " +
          "dbo.TaxID ON dbo.ID.ID = dbo.TaxID.ID " +
          "ORDER BY [№]";
            try
            {
                GetAndShowData(sqlQ, DataGrid1);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
            SumTax.Content = "Підприємство " + Convert.ToString(RunSQL("SELECT SUM(Tax) FROM TaxID"));
            SumTaxSal.Content = "Працівник " + Convert.ToString(RunSQL("SELECT SUM(TaxSalary) FROM TaxID"));
        }

        private void Button_Click_3(object sender, RoutedEventArgs e)
        {
            SumTax.Content = null;
            SumTaxSal.Content = null;
            string sqlQ = "SELECT TOP (100) PERCENT dbo.ID.ID AS [№], " +
          "dbo.ID.Name AS [Ім'я], " +
          "dbo.ID.Surname AS Прізвище, " +
          "dbo.ID.Patronymic AS [по батькові], " +
          "dbo.ID.Position AS Позиція," +
          "dbo.PSFull.FullPS AS [Сума нарахувань] " +
          "FROM     dbo.ID INNER JOIN " +
          "dbo.PSFull ON dbo.ID.ID = dbo.PSFull.ID " +
          "ORDER BY [№]";
            try
            {
                GetAndShowData(sqlQ, DataGrid1);
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
        private void Button_Click_4(object sender, RoutedEventArgs e)
        {
            MainWindow mw = new MainWindow();
            Hide();
            mw.Show();
        }
    }
}
