package ir.whoisAbel.roompractice.user.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ir.whoisAbel.roompractice.R
import ir.whoisAbel.roompractice.db.entities.User

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList: List<User>? = mutableListOf()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.firstName_txt)
        val lName: TextView = itemView.findViewById(R.id.lastName_txt)
        val id: TextView = itemView.findViewById(R.id.id_txt)
        val address: TextView = itemView.findViewById(R.id.address_txt)
        val age: TextView = itemView.findViewById(R.id.age_txt)
        val row: ConstraintLayout = itemView.findViewById(R.id.cl_row)
        val profile: ImageView = itemView.findViewById(R.id.iv_profile)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList?.get(position)
        holder.id.text = currentItem?.id.toString()
        holder.name.text = currentItem?.name
        holder.lName.text = currentItem?.lastName
        holder.address.text = currentItem?.address?.street
        holder.profile.load(currentItem?.profilePhoto)
        holder.age.text = currentItem?.age.toString()
        holder.row.setOnClickListener {
            if (currentItem != null)
                holder.itemView.findNavController()
                    .navigate(ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem))

        }

    }

    override fun getItemCount(): Int {
        return userList?.size ?: 0
    }

    fun setData(user: List<User>?) {
        this.userList = user
        notifyDataSetChanged()
    }

}